package migool.op.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.ListBox;

/**
 * 
 * @author Denis Migol
 *
 */
public final class GWTClientUtil {

	private GWTClientUtil() {
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
