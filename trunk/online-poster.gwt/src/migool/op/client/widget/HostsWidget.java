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
					addRow(host, result.get(host));
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		});
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
	 * @author Denis Migol
	 * 
	 */
	private final class SettingsDialog extends DialogBox {
		private final String host;
		private final HostConfigSerializable hc;

		private final TextBox username;
		private final TextBox password;
		private final CheckBox enabled;
		
		public SettingsDialog(final String host, final HostConfigSerializable hc) {
			super(false, true);

			this.host = host;
			this.hc = hc;

			setText("Settings");
			VerticalPanel vp = new VerticalPanel();
			vp.setWidth("100%");

			vp.add(new HTML("host"));

			TextBox hosttext = new TextBox();
			hosttext.setText(host);
			hosttext.setReadOnly(true);
			vp.add(hosttext);

			vp.add(new HTML("username"));

			username = new TextBox();
			//username.setText((hc == null) ? "" : hc.username);
			vp.add(username);

			vp.add(new HTML("password"));

			password = new TextBox();
			//password.setText((hc == null) ? "" : hc.password);
			vp.add(password);

			enabled = new CheckBox("enabled");
			//enabled.setValue((hc == null) ? false : hc.enabled);
			vp.add(enabled);
			
			setHostConfig();

			HorizontalPanel hp = new HorizontalPanel();
			hp.setWidth("100%");

			Button ok = new Button("ok", new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					hide();
					final HostConfigSerializable hostConfig = getHostConfig();

					postService.setHostConfig(hostConfig, new AsyncCallback<Void>() {

						@Override
						public void onSuccess(Void result) {
							int rowCount = getRowCount();
							for (int j = 0; j < rowCount; j++) {
								String text = HostsWidget.this.getText(j, 0);
								if (text.equals(host)) {
									int i = 1;
									final CheckBox cb = new CheckBox();
									cb.setEnabled(false);
									HostsWidget.this.setWidget(j, i++, new HTML(hostConfig.username));
									HostsWidget.this.setWidget(j, i++, new HTML(hostConfig.password));
									cb.setValue(hostConfig.enabled);
									HostsWidget.this.setWidget(j, i++, cb);
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
					hide();
				}
			});
			hp.add(cancel);

			vp.add(hp);
			add(vp);
			center();
			show();
		}
		
		private void setHostConfig() {
			username.setText((hc == null) ? "" : hc.username);
			password.setText((hc == null) ? "" : hc.password);
			enabled.setValue((hc == null) ? false : hc.enabled);
		}
		
		private HostConfigSerializable getHostConfig() {
			HostConfigSerializable ret = new HostConfigSerializable();
			ret.host = host;
			ret.username = username.getText();
			ret.password = password.getText();
			ret.enabled = enabled.getValue();
			return ret;
		}
	}

	private final void addRow(final String host, final HostConfigSerializable hc) {
		Hyperlink link = new Hyperlink(host, "");
		link.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				new SettingsDialog(host, hc);
			}
		});

		int rowCount = getRowCount();
		int i = 0;
		setWidget(rowCount, i++, link);
		CheckBox cb = new CheckBox();
		cb.setEnabled(false);
		if (hc != null) {
			setWidget(rowCount, i++, new HTML(hc.username));
			setWidget(rowCount, i++, new HTML(hc.password));
			cb.setValue(hc.enabled);
			setWidget(rowCount, i++, cb);
		} else {
			setWidget(rowCount, i++, new HTML(""));
			setWidget(rowCount, i++, new HTML(""));
			cb.setEnabled(false);
			cb.setValue(false);
			setWidget(rowCount, i++, cb);
		}
	}
}
