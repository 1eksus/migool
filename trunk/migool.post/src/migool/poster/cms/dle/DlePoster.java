package migool.poster.cms.dle;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import migool.host.auth.LoginResult;
import migool.host.auth.impl.HostLoginPassword;
import migool.host.upload.UploadResponse;
import migool.host.upload.UploadResult;
import migool.http.HttpClient;
import migool.http.message.HttpBody;
import migool.http.message.HttpRequest;
import migool.http.methods.HttpGet;
import migool.http.methods.HttpMethod;
import migool.http.methods.HttpPost;
import migool.post.internal.Image;
import migool.poster.PostResult;
import migool.util.LinkUtil;
import static migool.util.StringUtil.*;

/**
 * 
 * @author Denis Migol
 *
 */
public class DlePoster implements IDlePoster {
	
	public final HostLoginPassword hlp;
	public final HttpClient client;
	
	protected String httpRoot;
	protected boolean isLogged;

	/**
	 * @param hlp
	 * @param client
	 */
	public DlePoster(HostLoginPassword hlp, HttpClient client) {
		super();
		this.hlp = hlp;
		this.client = client;
		this.isLogged = false;
		this.httpRoot = LinkUtil.createHttpRoot(hlp.host);
	}

	/**
	 * 
	 * @param post
	 * @return
	 */
	public int post(DlePost post) {
		// TODO
		return PostResult.NOT_POSTED;
	}

	public int login() {
		try {
			String url = httpRoot;
			HttpMethod get = new HttpGet(url);
			client.execute(get);
			String html = get.getResponse().getBody().toString();
			
			String login = getContainsString(html, IDleConstants.LOGIN_INPUTS);
			String pass = getContainsString(html, IDleConstants.PASS_INPUTS);

			HttpMethod post = new HttpPost(url);
			post.getRequest().setBody(new HttpBody(login + "=" + hlp.login + "&" + pass + "=" + hlp.password + "&login=submit"));
			client.execute(post);
			html = post.getResponse().getBody().toString();

			isLogged = html.contains(hlp.login);
			return (isLogged) ? LoginResult.OK : LoginResult.NOT_LOGGED;
		} catch (Exception e) {
			return LoginResult.ERROR;
		}
	}
	
	public UploadResponse uploadImage(Image image) {
		if (!isLogged && (login() != LoginResult.OK)) {
			return new UploadResponse(UploadResult.ERROR, null);
		}
		// TODO
		try {
			String url = httpRoot + "/engine/images.php?area=short_story&add_id=";
			HttpMethod get = new HttpGet(url);
			get.getRequest().getHeaders().put(HttpRequest.REFERER, Arrays.asList(new String[] {httpRoot + IDleConstants.ADD_NEWS_PATH}));
			client.execute(get);

			HttpMethod post = new HttpPost(url);

			String boundary = "---------------------------26342427220055";
			String contentType = "multipart/form-data; boundary=" + boundary;
//			StringBuffer sb = new StringBuffer();
//			sb.append("--" + boundary + CRLF);
//			sb.append("Content-Disposition: form-data; name=\"subaction\"" + CRLF2);
//			sb.append("upload" + CRLF);
//			sb.append("--" + boundary + CRLF);
//			sb.append("Content-Disposition: form-data; name=\"area\"" + CRLF2);
//			sb.append("short_story" + CRLF);
//			sb.append("--" + boundary + CRLF);
//			sb.append("Content-Disposition: form-data; name=\"action\"" + CRLF2);
//			sb.append("quick" + CRLF);
//			sb.append("--" + boundary + CRLF);
//			sb.append("Content-Disposition: form-data; name=\"images_number\"" + CRLF2);
//			sb.append("1" + CRLF);
//			sb.append("--" + boundary + CRLF);
//			sb.append("Content-Disposition: form-data; name=\"file_1\"; filename=\"21.jpg\"" + CRLF);
//			// TODO
//			//sb.append("Content-Type: " + image.type + CRLF2);
//			sb.append("Content-Type: image/jpeg" + CRLF2);
			String temp = "--" + boundary + CRLF +
			"Content-Disposition: form-data; name=\"subaction\"" + CRLF2 +
			"upload" + CRLF +
			"--" + boundary + CRLF +
			"Content-Disposition: form-data; name=\"area\"" + CRLF2 +
			"short_story" + CRLF +
			"--" + boundary + CRLF +
			"Content-Disposition: form-data; name=\"action\"" + CRLF2 +
			"quick" + CRLF +
			"--" + boundary + CRLF +
			"Content-Disposition: form-data; name=\"images_number\"" + CRLF2 +
			"1" + CRLF +
			"--" + boundary + CRLF +
			"Content-Disposition: form-data; name=\"file_1\"; filename=\"21.jpg\"" + CRLF +
			"Content-Type: image/jpeg" + CRLF2;
			
			String temp2 = CRLF +
			"--" + boundary + CRLF +
			"Content-Disposition: form-data; name=\"imageurl\"" + CRLF2 +
			CRLF + "--" + boundary + CRLF +
			"Content-Disposition: form-data; name=\"serverfile\"" + CRLF2 +
			CRLF + "--" + boundary + CRLF +
			"Content-Disposition: form-data; name=\"t_size\"" + CRLF2 +
			450 + CRLF +
			"--" + boundary + CRLF +
			"Content-Disposition: form-data; name=\"t_seite\"" + CRLF2 +
			0 + CRLF +
			"--" + boundary + CRLF +
			"Content-Disposition: form-data; name=\"make_thumb\"" + CRLF2 +
			"make_thumb" + CRLF +
			"--" + boundary + CRLF +
			"Content-Disposition: form-data; name=\"make_watermark\"" + CRLF2 +
			"yes" + CRLF +
			"--" + boundary + "--" + CRLF;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			out.write(temp.getBytes());
			out.write(image.bytes);
			out.write(temp2.getBytes());
//			sb.append(new String(image.bytes) + CRLF);
//			sb.append("--" + boundary + CRLF);
//			sb.append("Content-Disposition: form-data; name=\"imageurl\"" + CRLF2);
//			sb.append(CRLF + "--" + boundary + CRLF);
//			sb.append("Content-Disposition: form-data; name=\"serverfile\"" + CRLF2);
//			sb.append(CRLF + "--" + boundary + CRLF);
//			sb.append("Content-Disposition: form-data; name=\"t_size\"" + CRLF2);
//			sb.append(450 + CRLF);
//			sb.append("--" + boundary + CRLF);
//			sb.append("Content-Disposition: form-data; name=\"t_seite\"" + CRLF2);
//			sb.append(0 + CRLF);
//			sb.append("--" + boundary + CRLF);
//			sb.append("Content-Disposition: form-data; name=\"make_thumb\"" + CRLF2);
//			sb.append("make_thumb" + CRLF);
//			sb.append("--" + boundary + CRLF);
//			sb.append("Content-Disposition: form-data; name=\"make_watermark\"" + CRLF2);
//			sb.append("yes" + CRLF);
//			sb.append("--" + boundary + "--");
//			byte[] bytes = sb.toString().getBytes();
//
//			post.getRequest().setBody(new HttpBody(sb.toString()));
			post.getRequest().setBody(new HttpBody(contentType, new ByteArrayInputStream(out.toByteArray())));

			Map<String, List<String>> headers = post.getRequest().getHeaders();
			headers.put(HttpRequest.REFERER, Arrays.asList(new String[]{url}));
			headers.put(HttpRequest.CONTENT_TYPE, Arrays.asList(new String[] {contentType}));
			headers.put(HttpRequest.CONTENT_LENGTH, Arrays.asList(new String[] {out.size() + ""}));

			client.execute(post);
			HttpBody body = post.getResponse().getBody();
			String html = body.toString();
			System.out.println(html);
		} catch (Exception e) {
			return new UploadResponse(UploadResult.ERROR, null);
		}
		return new UploadResponse(UploadResult.NOT_UPLOADED, null);
	}
}
