package migool.op.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		OutputStream out = resp.getOutputStream();
		
		resp.setHeader("Pragma", "no-cache");
		resp.setContentType(contentType);
		out.write(image);
		out.flush();
		out.close();
		System.out.println(image);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
