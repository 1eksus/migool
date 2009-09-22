package migool.http.message;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * 
 * @author Denis Migol
 *
 */
public class HttpBody {
	private String contentType;
	private byte[] body;
	private Charset charset = Charset.defaultCharset();
	
	public HttpBody() {
		body = new byte[0];
	}
	
	public HttpBody(String body) {
		this();
		this.body = body.getBytes();
	}
	
	public HttpBody(String contentType, InputStream in) throws IOException {
		this(contentType);
		read(in);
	}
	
	public byte[] getBytes() {
		return body;
	}

	public int length() {
		return body.length;
	}
	
	public void setCharset(Charset charset) {
		this.charset = charset;
	}
	
	public Charset getCharset() {
		return charset;
	}
	
	public void setContentType(String type) {
		this.contentType = type;
	}
	
	public String getContentType() {
		return contentType;
	}
	
	public boolean isText() {
		return (contentType != null) && (contentType.startsWith("text/"));
	}
	
	public void read(InputStream in) throws IOException {
		int length = 0;
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		while ((length = in.available()) > 0) {
			byte[] buf = new byte[length];
			int read = 0;
			while ((read = in.read(buf)) > 0) {
				bo.write(buf, 0, read);
			}
		}
		body = bo.toByteArray();
	}
	
	public void write(OutputStream out) throws IOException {
		// TODO
		DataOutputStream dout = new DataOutputStream(out);
		dout.writeBytes(new String(body, charset));
	}

	public String toString() {
		return new String(body, charset);
	}
}
