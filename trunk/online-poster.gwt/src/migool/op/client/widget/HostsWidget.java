package migool.op.client.widget;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import migool.op.client.PostServiceAsync;
import migool.op.client.serializable.HostConfigSerializable;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * @author Denis Migol
 * 
 */
public final class HostsWidget extends FlexTable {
	private final PostServiceAsync postService;

	public HostsWidget(final PostServiceAsync postService) {
		super();
		this.postService = postService;
		setWidth("100%");

		setHeaders();
	}

	private final void setHeaders() {
		int i = 0;
		setWidget(0, i++, new HTML("host"));
		setWidget(0, i++, new HTML("username"));
		setWidget(0, i++, new HTML("password"));
		setWidget(0, i++, new HTML("enabled"));
	}

	/**
	 * 
	 * @param postService
	 * @return
	 */
	public static final Widget create(final PostServiceAsync postService) {
		final FlexTable ft = new FlexTable();
		ft.setWidth("100%");

		int i = 0;
		ft.setWidget(0, i++, new HTML("host"));
		ft.setWidget(0, i++, new HTML("username"));
		ft.setWidget(0, i++, new HTML("password"));
		ft.setWidget(0, i++, new HTML("enabled"));

		final ArrayList<String> hosts = new ArrayList<String>();
		postService.getHosts(new AsyncCallback<List<String>>() {
			@Override
			public void onSuccess(List<String> result) {
				for (String host : result) {
					hosts.add(host);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		});

		postService.getHostConfigs(new AsyncCallback<Map<String, HostConfigSerializable>>() {
			@Override
			public void onSuccess(Map<String, HostConfigSerializable> result) {
				for (String host : hosts) {
					addRow(postService, ft, host, result.get(host));
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		});
		return ft;
	}

	private static final void addRow(final PostServiceAsync postService, final FlexTable ft, final String host,
			final HostConfigSerializable hc) {
		Hyperlink link = new Hyperlink(host, "");
		link.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final DialogBox db = new DialogBox(false, true);
				db.setText("Settings");
				VerticalPanel vp = new VerticalPanel();
				vp.setWidth("100%");

				vp.add(new HTML("host"));

				TextBox hosttext = new TextBox();
				hosttext.setText(host);
				hosttext.setReadOnly(true);
				vp.add(hosttext);

				vp.add(new HTML("username"));

				final TextBox username = new TextBox();
				username.setText((hc == null) ? "" : hc.username);
				vp.add(username);

				vp.add(new HTML("password"));

				final TextBox password = new TextBox();
				password.setText((hc == null) ? "" : hc.password);
				vp.add(password);

				final CheckBox enabled = new CheckBox("enabled");
				enabled.setValue((hc == null) ? false : hc.enabled);
				vp.add(enabled);
				
				HorizontalPanel hp = new HorizontalPanel();
				hp.setWidth("100%");

				Button ok = new Button("ok", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						db.hide();
						final HostConfigSerializable hostConfig = new HostConfigSerializable();
						hostConfig.host = host;
						hostConfig.username = username.getText();
						hostConfig.password = password.getText();
						hostConfig.enabled = enabled.getValue();

						postService.setHostConfig(hostConfig, new AsyncCallback<Void>() {

							@Override
							public void onSuccess(Void result) {
								for (int j = 0; j < ft.getRowCount(); j++) {
									String text = ft.getText(j, 0);
									if (text.equals(host)) {
										int i = 1;
										final CheckBox cb = new CheckBox();
										cb.setEnabled(false);
										ft.setWidget(j, i++, new HTML(hostConfig.username));
										ft.setWidget(j, i++, new HTML(hostConfig.password));
										cb.setValue(hostConfig.enabled);
										ft.setWidget(j, i++, cb);
									}
								}
							}

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
							}
						});
					}
				});
				hp.add(ok);
				
				Button cancel = new Button("cancel", new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						db.hide();
					}
				});
				hp.add(cancel);
				
				vp.add(hp);
				db.add(vp);
				db.center();
				db.show();
			}
		});
		
		int numRows = ft.getRowCount();
		int i = 0;
		ft.setWidget(numRows, i++, link);
		CheckBox cb = new CheckBox();
		cb.setEnabled(false);
		if (hc != null) {
			ft.setWidget(numRows, i++, new HTML(hc.username));
			ft.setWidget(numRows, i++, new HTML(hc.password));
			cb.setValue(hc.enabled);
			ft.setWidget(numRows, i++, cb);
		} else {
			ft.setWidget(numRows, i++, new HTML(""));
			ft.setWidget(numRows, i++, new HTML(""));
			cb.setEnabled(false);
			cb.setValue(false);
			ft.setWidget(numRows, i++, cb);
		}
	}
}
