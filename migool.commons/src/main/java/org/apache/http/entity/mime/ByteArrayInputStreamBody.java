package org.apache.http.entity.mime;

import java.io.ByteArrayInputStream;

import org.apache.http.entity.mime.content.InputStreamBody;

/**
 * 
 * @author Denis Migol
 *
 */
public class ByteArrayInputStreamBody extends InputStreamBody {

	private final long contentLength;

	public ByteArrayInputStreamBody(final byte[] bytes, final String mimeType, final String filename) {
		super(new ByteArrayInputStream(bytes), mimeType, filename);
		contentLength = bytes.length;
	}

	public ByteArrayInputStreamBody(final byte[] bytes, final String filename) {
		super(new ByteArrayInputStream(bytes), filename);
		contentLength = bytes.length;
	}

	public long getContentLength() {
		return contentLength;
	}
}
