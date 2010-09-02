package migool.upload;

import java.io.IOException;

import org.htmlparser.util.ParserException;

import migool.entity.CaptchaEntity;
import migool.entity.FileEntity;

/**
 * 
 * @author Denis Migol
 * 
 */
public interface IFileEntityUploader {
	/**
	 * 
	 * @return
	 */
	CaptchaEntity getUploadCaptcha();

	/**
	 * 
	 * @param img
	 * @return
	 * @throws UploadException
	 * @throws ParserException
	 * @throws IOException
	 */
	String upload(FileEntity img) throws UploadException, ParserException, IOException;
}
