package migool.op.client.widget;

import java.util.List;

import migool.op.client.PostServiceAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * @author Denis Migol
 *
 */
public final class HostsWidget {
	private HostsWidget() {
	}

	/**
	 * 
	 * @param postService
	 * @return
	 */
	public static final Widget create(final PostServiceAsync postService) {
		final VerticalPanel vp = new VerticalPanel();
		vp.setWidth("100%");

		postService.getHosts(new AsyncCallback<List<String>>() {
			@Override
			public void onSuccess(List<String> result) {
				for (String host : result) {
					vp.add(new HTML(host));
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		});
		return vp;
	}
}
