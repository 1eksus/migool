package migool.op.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * 
 * @author Denis Migol
 *
 */
@RemoteServiceRelativePath("category")
public interface CategoryService extends RemoteService {
	List<String> getCategories();
}
