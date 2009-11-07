package migool.op.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import migool.util.EmptyChecker;
import migool.util.IOUtil;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 
 * @author Denis Migol
 *
 */
@SuppressWarnings("serial")
public class UploadServlet extends HttpServlet {
	/**
	 * 
	 * @author Denis Migol
	 *
	 */
	private static final class FileEntity {
		String contentType;
		byte[] bytes;
	}

	private static final int MAX_FILES = 500;
	private static final int FIRST_FILE = 0;
	private static int currentFile = FIRST_FILE;
	private static final Map<String, FileEntity> files = new ConcurrentHashMap<String, FileEntity>();

	public static final String upload(String contentType, byte[] bytes) {
		FileEntity file = new FileEntity();
		if (EmptyChecker.isNotNullOrEmpty(contentType)) {
			file.contentType = contentType;
		}
		file.bytes = bytes;
		String fileId = nextId();
		files.put(fileId, file);
		return fileId;
	}
	
	private static String nextId() {
		if (currentFile >= MAX_FILES) {
			currentFile = FIRST_FILE;
		}
		return currentFile++ + "";
	}

//	/**
//	 * 
//	 * @param fileName
//	 * @return
//	 */
//	private String nextId(String fileName) {
//		String name = (new File(fileName)).getName();
//		int pos = name.lastIndexOf('.');
//		if (pos > -1) {
//			return nextId() + name.substring(pos, name.length());
//		} else {
//			return nextId();
//		}
//	}

	/**
	 * 
	 * @return
	 */
	private String getId(String uri) {
		if ("".equals(uri) || uri == null) {
			return null;
		}
		int pos = uri.lastIndexOf('/');
		int length = uri.length();
		if ((pos > -1) && (pos < (length - 1))) {
			return uri.substring(pos + 1, length);
		} else {
			return "";
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = getId(req.getRequestURI());
		if (files.containsKey(id)) {
			FileEntity file = files.get(id);
			OutputStream out = resp.getOutputStream();
			resp.setHeader("Pragma", "no-cache");
			if (EmptyChecker.isNotNullOrEmpty(file.contentType)) {
				resp.setContentType(file.contentType);
			}
			out.write(file.bytes);
			out.flush();
			out.close();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		try {
			ServletFileUpload upload = new ServletFileUpload();
			// Set the UTF-8 encoding to grab the correct uploaded filename, especially for Chinese
			upload.setHeaderEncoding("UTF-8");

			// Parse the request
			FileItemIterator iter = upload.getItemIterator(req);
			while (iter.hasNext()) {
				FileItemStream item = iter.next();
				InputStream stream = item.openStream();
				if (!item.isFormField()) {
					out.print(req.getRequestURL() + "/" + upload(item.getContentType(), IOUtil.toByteArray(stream)));
					out.flush();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
}
