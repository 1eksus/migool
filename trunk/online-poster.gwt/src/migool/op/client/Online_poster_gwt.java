package migool.op.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Online_poster_gwt implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
		splitPanel.setSize("100%", "100%");
		splitPanel.setSplitPosition("30%");
		splitPanel.add(new HTML("<b>asdfas</b><br>fasd"));
		splitPanel.add(new HTML("<b>asdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdf</b>"));

		RootPanel.get().add(splitPanel);
	}
}
