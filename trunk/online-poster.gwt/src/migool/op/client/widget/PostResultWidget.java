package migool.op.client.widget;

import java.util.HashMap;
import java.util.Map;

import migool.op.client.PostServiceAsync;
import migool.op.client.serializable.HostConfigSerializable;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;

/**
 * 
 * @author Denis Migol
 *
 */
public class PostResultWidget extends FlexTable {
	private final PostServiceAsync service;
	private final Map<String, HostConfigSerializable> hosts = new HashMap<String, HostConfigSerializable>();

	/**
	 * 
	 */
	public PostResultWidget(final PostServiceAsync postService) {
		super();

		this.service = postService;

		service.getHostConfigs(new AsyncCallback<Map<String,HostConfigSerializable>>() {

			@Override
			public void onSuccess(Map<String, HostConfigSerializable> result) {
				hosts.putAll(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
