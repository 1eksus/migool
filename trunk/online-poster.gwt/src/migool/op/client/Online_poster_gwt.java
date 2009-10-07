package migool.op.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Online_poster_gwt implements EntryPoint {

	private final PostServiceAsync postService = GWT.create(PostService.class);
	HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();

	/**
	 * 
	 * @return
	 */
	private Widget createSitesWidget() {
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

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootPanel.get().add(splitPanel);
		splitPanel.setSize("100%", "100%");
		splitPanel.setSplitPosition("30%");
		VerticalPanel vp = new VerticalPanel();
		vp.setWidth("100%");

		final Hyperlink post = new Hyperlink("post", "");
		post.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Widget widget = splitPanel.getRightWidget();
				if (widget != null) {
					splitPanel.remove(widget);
				}
				// splitPanel.add(createPostWidget());
				splitPanel.add(PostWidget.create(postService));
			}
		});
		vp.add(post);
		final Hyperlink hosts = new Hyperlink("hosts", "");
		hosts.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Widget widget = splitPanel.getRightWidget();
				if (widget != null) {
					splitPanel.remove(widget);
					splitPanel.add(createSitesWidget());
				}
			}
		});
		vp.add(hosts);

		splitPanel.add(vp);
		// splitPanel.add(createPostWidget());
		splitPanel.add(PostWidget.create(postService));
	}
}
