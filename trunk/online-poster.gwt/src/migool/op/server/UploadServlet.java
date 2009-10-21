package migool.op.server;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	private String contentType;
	private byte[] image;

	/**
	 * 
	 * @author Denis Migol
	 *
	 */
	private static final class FileEntity {
		String contentType;
		byte[] bytes;
	}

	private HashMap<String, FileEntity> files = new HashMap<String, FileEntity>();

	private String nextId(String fileName) {
		String name = (new File(fileName)).getName();
		int pos = name.lastIndexOf('.');
		if (pos > -1) {
			return files.size() + name.substring(pos, name.length());
		} else {
			return files.size() + "";
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getRequestURI());
		OutputStream out = resp.getOutputStream();

		resp.setHeader("Pragma", "no-cache");
		resp.setContentType(contentType);
		out.write(image);
		out.flush();
		out.close();
		//System.out.println(image);
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
					contentType = item.getContentType();
					image = IOUtil.toByteArray(stream);

					FileEntity file = new FileEntity();
					file.contentType = item.getContentType();
					file.bytes = IOUtil.toByteArray(stream);
					String fileId = nextId(item.getFieldName());
					files.put(fileId, file);
					out.print(fileId);
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
