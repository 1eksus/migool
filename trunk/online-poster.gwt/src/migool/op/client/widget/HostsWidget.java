package migool.op.client.widget;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import migool.op.client.PostServiceAsync;
import migool.op.client.serializable.HostConfigSerializable;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
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
	private final PostServiceAsync service;
	private TreeMap<String, HostConfigSerializable> hostConfigs = new TreeMap<String, HostConfigSerializable>();

	/**
	 * 
	 * @param postService
	 */
	public HostsWidget(final PostServiceAsync postService) {
		super();
		service = postService;
		setWidth("100%");

		setHeaders();

		final ArrayList<String> hosts = new ArrayList<String>();
		service.getHosts(new AsyncCallback<List<String>>() {
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

		service.getHostConfigs(new AsyncCallback<Map<String, HostConfigSerializable>>() {
			@Override
			public void onSuccess(Map<String, HostConfigSerializable> result) {
				hostConfigs.putAll(result);
				for (String host : hosts) {
					addRow(host);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		});
	}

	/**
	 * 
	 */
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
		private HostConfigSerializable hc;

		private final TextBox username = new TextBox();
		private final TextBox password = new TextBox();
		private final CheckBox enabled = new CheckBox("enabled");

		/**
		 * 
		 * @param host
		 * @param hc
		 */
		public SettingsDialog(final String host) {
			super(false, true);

			this.host = host;
			this.hc = hostConfigs.get(host);

			setText("Settings");

			add(createBody());
			center();
			show();
		}

		/**
		 * 
		 * @return
		 */
		private VerticalPanel createBody() {
			VerticalPanel vp = new VerticalPanel();
			vp.setWidth("100%");

			vp.add(new HTML("host"));

			TextBox hosttext = new TextBox();
			hosttext.setText(host);
			hosttext.setReadOnly(true);
			vp.add(hosttext);

			vp.add(new HTML("username"));
			vp.add(username);

			vp.add(new HTML("password"));
			vp.add(password);

			vp.add(enabled);

			setHostConfig();

			HorizontalPanel hp = new HorizontalPanel();
			hp.setWidth("100%");

			Button ok = new Button("ok", new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					hide();
					final HostConfigSerializable hostConfig = getHostConfig();

					service.setHostConfig(hostConfig, new AsyncCallback<Void>() {

						@Override
						public void onSuccess(Void result) {
							int row = getRowWithHost(host);
							if (row > 0) {
								changeRow(row, hostConfig);
							}
							hostConfigs.put(host, hostConfig);
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
			return vp;
		}

		/**
		 * 
		 * @param host
		 * @return
		 */
		private int getRowWithHost(String host) {
			int rowCount = getRowCount();
			String text = null;
			for (int j = 0; j < rowCount; j++) {
				text = HostsWidget.this.getText(j, 0);
				if (text.equals(host)) {
					return j;
				}
			}
			return -1;
		}

		/**
		 * 
		 */
		private void setHostConfig() {
			username.setText((hc == null) ? "" : hc.username);
			password.setText((hc == null) ? "" : hc.password);
			enabled.setValue((hc == null) ? false : hc.enabled);
		}

		/**
		 * 
		 * @return
		 */
		private HostConfigSerializable getHostConfig() {
			HostConfigSerializable ret = new HostConfigSerializable();
			ret.host = host;
			ret.username = username.getText();
			ret.password = password.getText();
			ret.enabled = enabled.getValue();
			return ret;
		}
	}

	/**
	 * 
	 * @param host
	 * @param hc
	 */
	private final void addRow(final String host) {
		Hyperlink link = new Hyperlink(host, "");
		link.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				new SettingsDialog(host);
			}
		});

		int rowCount = getRowCount();
		setWidget(rowCount, 0, link);

		changeRow(rowCount, hostConfigs.get(host));
	}

	/**
	 * 
	 * @param row
	 * @param col
	 * @param hc
	 */
	private final void changeRow(final int row, final HostConfigSerializable hc) {
		int i = 1;
		final CheckBox cb = new CheckBox();
		//cb.setEnabled(false);
		cb.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if (hc != null) {
					hc.enabled = event.getValue();
					service.setHostConfig(hc, new AsyncCallback<Void>() {

						@Override
						public void onSuccess(Void result) {
							changeRow(row, hc);
							hostConfigs.put(hc.host, hc);
						}

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
						}
					});
				}
			}
		});
		if (hc != null) {
			setWidget(row, i++, new HTML(hc.username));
			setWidget(row, i++, new HTML(hc.password));
			cb.setValue(hc.enabled);
			setWidget(row, i++, cb);
		} else {
			setWidget(row, i++, new HTML(""));
			setWidget(row, i++, new HTML(""));
			cb.setEnabled(false);
			cb.setValue(false);
			setWidget(row, i++, cb);
		}
	}
}
