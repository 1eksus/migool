package migool.op.client.widget;

import migool.op.client.PostServiceAsync;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * @author Denis Migol
 *
 */
public class CaptchaDialogWidget {

	/**
	 * 
	 * @return
	 */
	public Widget create(final PostServiceAsync postService) {
		// TODO
		DialogBox db = new DialogBox();
		VerticalPanel vp = new VerticalPanel();
		
		db.add(vp);
		return db;
	}
}
