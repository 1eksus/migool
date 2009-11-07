package migool.op.client;

import migool.op.client.widget.HostsWidget;
import migool.op.client.widget.PostWidget;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
	//private final HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
		splitPanel.setSize("100%", "100%");
		splitPanel.setSplitPosition("100px");
		VerticalPanel left = new VerticalPanel();
		left.setWidth("100%");

		final Hyperlink post = new Hyperlink("post", "");
		post.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Widget widget = splitPanel.getRightWidget();
				if (widget != null) {
					splitPanel.remove(widget);
				}
				//splitPanel.add(PostWidget.create(postService));
				splitPanel.add(new PostWidget(postService));
			}
		});
		left.add(post);

		final Hyperlink hosts = new Hyperlink("hosts", "");
		hosts.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Widget widget = splitPanel.getRightWidget();
				if (widget != null) {
					splitPanel.remove(widget);
					//splitPanel.add(HostsWidget.create(postService));
					splitPanel.add(new HostsWidget(postService));
				}
			}
		});
		left.add(hosts);

		splitPanel.add(left);
		//splitPanel.add(PostWidget.create(postService));
		splitPanel.add(new PostWidget(postService));
		RootPanel.get().add(splitPanel);
	}
}
