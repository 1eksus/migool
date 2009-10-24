package migool.op.client.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import migool.op.client.serializable.PostResponseSerializable;

import com.google.gwt.user.client.ui.ListBox;

/**
 * 
 * @author Denis Migol
 *
 */
public final class GWTClientUtil {

	static final String _100 = "100%";

	private GWTClientUtil() {
	}
	
	private static final HashMap<Integer, String> postResponseCodes;
	
	static {
		postResponseCodes = new HashMap<Integer, String>();
		postResponseCodes.put(PostResponseSerializable.OK, "OK");
		postResponseCodes.put(PostResponseSerializable.ERROR, "ERROR");
		postResponseCodes.put(PostResponseSerializable.NOT_POSTED, "NOT POSTED");
	}

	/**
	 * 
	 * @param code
	 * @return
	 */
	public static final String codeToMessage(PostResponseSerializable response) {
		return postResponseCodes.get(response.code);
	}

	/**
	 * 
	 * @param lb
	 * @return
	 */
	public static final List<String> getSelectedItemsText(ListBox lb) {
		List<String> ret = new ArrayList<String>();
		int begin = lb.getSelectedIndex();
		if (begin > -1) {
			int size = lb.getItemCount();
			for (int i = begin; i < size; i++) {
				if (lb.isItemSelected(i)) {
					ret.add(lb.getItemText(i));
				}
			}
		}
		return ret;
	}
}
