package migool.op.client.widget;

import static migool.op.client.widget.GWTClientUtil.*;

import java.util.HashMap;
import java.util.Map;

import migool.op.client.PostServiceAsync;
import migool.op.client.serializable.HostConfigSerializable;
import migool.op.client.serializable.PostResponseSerializable;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;

/**
 * 
 * @author Denis Migol
 * 
 */
public class PostResultWidget extends FlexTable {
	private final PostServiceAsync service;
	private final HashMap<String, HostConfigSerializable> hosts = new HashMap<String, HostConfigSerializable>();

	/**
	 * 
	 */
	public PostResultWidget(final PostServiceAsync postService) {
		super();

		this.service = postService;

		service.getHostConfigs(new AsyncCallback<Map<String, HostConfigSerializable>>() {

			@Override
			public void onSuccess(Map<String, HostConfigSerializable> result) {
				hosts.putAll(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		});
		setHeaders();

		final Object[] hosts = this.hosts.keySet().toArray();
		int size = hosts.length;
		for (int i = 0; i < size; i++) {
			final String host = hosts[i] + "";
			service.post(host, new AsyncCallback<PostResponseSerializable>() {

				@Override
				public void onSuccess(PostResponseSerializable result) {
					addRow(host, result);
				}

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
				}
			});
		}
	}

	private final void setHeaders() {
		int i = 0;
		setWidget(0, i++, new HTML("host"));
		setWidget(0, i++, new HTML("result"));
		setWidget(0, i++, new HTML("message"));
		setWidget(0, i++, new HTML("url"));
	}
	
	private final String createHttpRoot(String host) {
		return "http://" + host;
	}

	private final void addRow(final String host, final PostResponseSerializable response) {
		int rowCount = getRowCount();
		Hyperlink link = new Hyperlink(host, createHttpRoot(host));
		int i = 0;
		setWidget(rowCount, i++, link);
		setWidget(rowCount, i++, new HTML(codeToMessage(response)));
		setWidget(rowCount, i++, new HTML(response.message));
		String url = response.url;
		if (url != null && !"".equals(url)) {
			setWidget(rowCount, i++, new Hyperlink("view", url));
		} else {
			setWidget(rowCount, i++, new HTML("-"));
		}
	}
}
