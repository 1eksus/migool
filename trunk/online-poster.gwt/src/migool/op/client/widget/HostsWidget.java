package migool.op.client.widget;

import java.util.List;

import migool.op.client.PostServiceAsync;
import migool.op.client.serializable.HostConfigSerializable;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
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
		final FlexTable ft = new FlexTable();
		ft.setWidth("100%");
		
		postService.getHostConfigs(new AsyncCallback<List<HostConfigSerializable>>() {
			
			@Override
			public void onSuccess(List<HostConfigSerializable> result) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});

		postService.getHosts(new AsyncCallback<List<String>>() {
			@Override
			public void onSuccess(List<String> result) {
				for (String host : result) {
					//addRow(ft);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		});
		return ft;
	}

	private static final void addRow(final FlexTable ft, HostConfigSerializable hc) {
		int numRows = ft.getRowCount();
	}
}
