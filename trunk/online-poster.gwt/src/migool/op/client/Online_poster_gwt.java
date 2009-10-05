package migool.op.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Online_poster_gwt implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		//RootPanel.get().setSize("100%", "100%");
		DockPanel dockPanel = new DockPanel();
		//dockPanel.setSize("100%", "100%");
		RootPanel.get().add(dockPanel);
		
		dockPanel.add(new HTML("north"), DockPanel.NORTH);
		
		TabPanel tabPanel = new TabPanel();
		//tabPanel.setSize("100%", "100%");
		dockPanel.add(tabPanel, DockPanel.CENTER);
		dockPanel.setCellVerticalAlignment(tabPanel, HasVerticalAlignment.ALIGN_TOP);
		
		HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
		splitPanel.setSize("100%", "100%");
		splitPanel.add(new HTML("<b>asdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdf</b>"));
		splitPanel.add(new HTML("<b>asdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdf</b>"));
		tabPanel.add(splitPanel, "tab1");
	}
}
