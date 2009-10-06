package migool.op.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * 
 * @author Denis Migol
 *
 */
public interface CategoryServiceAsync {
	void getCategories(AsyncCallback<List<String>> callback);
}
