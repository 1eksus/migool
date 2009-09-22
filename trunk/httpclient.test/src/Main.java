import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import jd.nutils.encoding.Encoding;
import jd.parser.html.Form;
import jd.parser.html.InputField;

import migool.util.DebugUtil;
import migool.util.EmptyChecker;
import migool.util.LinkUtil;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 * 
 * @author Denis Migol
 * 
 */
public class Main {
	public static final String HOST = "dle80";
	public static final String SITE = LinkUtil.createHttpRoot(HOST);
	private static final HttpClient client = new DefaultHttpClient();
	private static final String FILENAME = "D:\\Den\\21.jpg";

	public static final void testGet() {
		HttpUriRequest get = new HttpGet(SITE);
		try {
			HttpResponse response = client.execute(get);
			response.getEntity().getContent().close();
			// System.out.println(DebugUtil.print(response.getEntity().getContent()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final void testLogin() {
		HttpPost post = new HttpPost(SITE);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("login_name", "dle"));
		params.add(new BasicNameValuePair("login_password", "dle"));
		params.add(new BasicNameValuePair("login", "submit"));
		try {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
			post.setEntity(entity);
			HttpResponse response = client.execute(post);
			response.getEntity().getContent().close();
			// System.out.println(DebugUtil.print(response.getEntity().getContent()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final void testUploadImage() {
		HttpPost post = new HttpPost(SITE + "/engine/images.php?area=short_story&add_id=");
		try {
			MultipartEntity entity = new MultipartEntity();
			entity.addPart("subaction", new StringBody("upload"));
			entity.addPart("area", new StringBody("short_story"));
			entity.addPart("action", new StringBody("quick"));
			entity.addPart("images_number", new StringBody("1"));
			entity.addPart("file_1", new FileBody(new File(FILENAME)));
			entity.addPart("imageurl", new StringBody(""));
			entity.addPart("serverfile", new StringBody(""));
			entity.addPart("t_size", new StringBody("450"));
			entity.addPart("t_seite", new StringBody("0"));
			entity.addPart("make_thumb", new StringBody("make_thumb"));
			entity.addPart("make_watermark", new StringBody("yes"));
			post.setEntity(entity);
			HttpResponse response = client.execute(post);
			response.getEntity().getContent().close();
			// System.out.println(DebugUtil.print(response.getEntity().getContent()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// public static final void testPost() {
	// HttpPost post = new HttpPost(SITE + "/?do=addnews");
	// List<NameValuePair> params = new ArrayList<NameValuePair>();
	// params.add(new BasicNameValuePair("title", "test_post"));
	// //params.add(new BasicNameValuePair("alt_name", "alt_name"));
	// params.add(new BasicNameValuePair("catlist[]", "1"));
	// params.add(new BasicNameValuePair("short_story", "short_story"));
	// params.add(new BasicNameValuePair("full_story", "full_story"));
	// params.add(new BasicNameValuePair("tags", "tags"));
	// params.add(new BasicNameValuePair("add", "submit"));
	// try {
	// post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
	// HttpResponse response = client.execute(post);
	// //response.getEntity().getContent().close();
	// System.out.println(DebugUtil.print(response.getEntity().getContent()));
	// } catch (Exception e) {
	// }
	// }
	
	private static final List<String> getSelectValues(String html) {
		// TODO
		List<String> ret = new ArrayList<String>();
		return ret;
	}
	
	private static final String getHtmlTagNameByAttributeNameValue(String html, String attrNameValue) {
		return null;
	}
	
	public static final void testPost() {
		String url = SITE + "/index.php?do=addnews";
		HttpGet get = new HttpGet(url);
		try {
			HttpResponse response = client.execute(get);
			String html = DebugUtil.print(response.getEntity().getContent());
			Form[] forms = Form.getForms(html);
			Form pForm = null;
			for (Form form : forms) {
				// System.out.println(form);
				if (form.hasInputFieldByName("title")) {
					pForm = form;
					break;
				}
			}
			System.out.println(pForm);
			Set<String> exceptions = new TreeSet<String>(Arrays.asList(new String[] { "nview" }));
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			for (InputField input : pForm.getInputFields()) {
				String name = Encoding.htmlDecode(input.getKey());
				if (!exceptions.contains(name)) {
					String type = Encoding.htmlDecode(input.getType());
					String value = Encoding.htmlDecode(input.getValue());
					System.out.println(name + "(" + type + ")" + value);
					if (type != null && EmptyChecker.isNotNullOrEmpty(value)) {
						params.add(new BasicNameValuePair(name, value));
					} else {
						if ("catlist[]".equals(name)) { // type == null
							params.add(new BasicNameValuePair(name, "1"));
						} else if (EmptyChecker.isNullOrEmpty(value)) {
							if ("text".equals(type)) {
								params.add(new BasicNameValuePair(name, name));
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		testGet();
		testLogin();
		// testUploadImage();
		testPost();
	}
}