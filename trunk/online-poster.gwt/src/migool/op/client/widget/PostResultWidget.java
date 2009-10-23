package migool.op.client.widget;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import migool.op.client.PostServiceAsync;
import migool.op.client.serializable.HostConfigSerializable;
import migool.op.client.serializable.PostResponseSerializable;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;

/**
 * 
 * @author Denis Migol
 *
 */
public class PostResultWidget extends FlexTable {
	private final PostServiceAsync service;
	private final Map<String, HostConfigSerializable> hosts = new HashMap<String, HostConfigSerializable>();
	//private final Map<String, HostConfigSerializable> hosts = new Hashtable<String, HostConfigSerializable>();

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
		setHeaders();
		
		Set<String> keySet = hosts.keySet();
		for (String host : keySet) {
			service.post(host, new AsyncCallback<PostResponseSerializable>() {
				
				@Override
				public void onSuccess(PostResponseSerializable result) {
					// TODO Auto-generated method stub
					
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
//		setWidget(0, i++, new HTML("username"));
//		setWidget(0, i++, new HTML("password"));
//		setWidget(0, i++, new HTML("enabled"));
	}

	private final void addRow(final String host) {
		
	}
}
