package migool.op.server;

import java.util.ArrayList;
import java.util.List;

import migool.op.client.PostService;
import migool.post.category.Categories;
import migool.post.category.Category;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * 
 * @author Denis Migol
 *
 */
@SuppressWarnings("serial")
public class PostServiceImpl extends RemoteServiceServlet implements
		PostService {

	@Override
	public List<String> getCategories() {
		List<String> ret = new ArrayList<String>();
		for (Category cat : Categories.CATS) {
			ret.add(cat.name);
			System.out.println(cat.name);
		}
		return ret;
	}

}
