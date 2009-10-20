package migool.op.client.widget;

import migool.op.client.PostServiceAsync;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * @author Denis Migol
 *
 */
public class CaptchaDialog extends DialogBox {

	/**
	 * 
	 * @return
	 */
	public CaptchaDialog(final PostServiceAsync postService) {
		super(false, true);
		VerticalPanel vp = new VerticalPanel();
		add(vp);
	}
}
