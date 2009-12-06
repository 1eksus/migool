package migool.poster.cms.dle.test;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import migool.host.auth.LoginPassword;
import migool.post.category.Category;
import migool.poster.cms.dle.IDlePost;

/**
 * 
 * @author Denis Migol
 *
 */
public interface IConstants {
	String LOGIN = "dle";
	String PASS = "dle";

	// Test hosts:
	public static final String HOST_DLE_67 = "dle67";
	public static final String HOST_DLE_70 = "dle70";
	public static final String HOST_DLE_72 = "dle72";
	public static final String HOST_DLE_73 = "dle73";
	public static final String HOST_DLE_75 = "dle75";
	public static final String HOST_DLE_80 = "www.dle80";
	public static final String HOST_DLE_82 = "dle82";
	
	//
	public static final LoginPassword LOGIN_PASSWORD = new LoginPassword(LOGIN, PASS);
	
	public IDlePost POST = new IDlePost() {
		
		@Override
		public String getUrl() {
			return "урла";
		}
		
		@Override
		public String getTitle() {
			return "заголовок";
		}
		
		@Override
		public List<String> getTags() {
			return Arrays.asList(new String[]{"тэг_1", "тэг_2", "тэг 3"});
		}
		
		@Override
		public String getShortStory() {
			return "краткая новость";
		}
		
		@Override
		public Properties getInputs() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getFullStory() {
			return "полная новость";
		}
		
		@Override
		public List<Category> getCategories() {
			// TODO Auto-generated method stub
			return null;
		}
	};
}