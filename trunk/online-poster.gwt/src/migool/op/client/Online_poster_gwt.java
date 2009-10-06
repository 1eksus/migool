package migool.op.client;

import migool.post.category.Categories;
import migool.post.category.Category;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Online_poster_gwt implements EntryPoint {

	/**
	 * 
	 * @return
	 */
	private Widget createPostWidget() {
		VerticalPanel vp = new VerticalPanel();
		//vp.setSize("100%", "100%");
		vp.setWidth("100%");
		
		vp.add(new HTML("Заголовок"));

		TextBox tb = new TextBox();
		tb.setName("title");
		tb.setWidth("100%");
		vp.add(tb);

		vp.add(new HTML("URL"));

		tb = new TextBox();
		tb.setName("url");
		tb.setWidth("100%");
		vp.add(tb);

		vp.add(new HTML("Категории"));

		ListBox lb = new ListBox(true);
		lb.setName("cats");
		for (Category cat : Categories.CATS) {
			lb.addItem(cat.name);
		}
//		lb.addItem("cat1 cat1 cat1 cat1");
//		lb.addItem("cat2");
		vp.add(lb);

		vp.add(new HTML("Картинка"));

		FileUpload fu = new FileUpload();
		fu.setName("image");
		fu.setWidth("100%");
		vp.add(fu);
		
		vp.add(new HTML("Текст новости"));
		
		TextArea ta = new TextArea();
		ta.setName("story");
		ta.setWidth("100%");
		ta.setHeight("20em");
		vp.add(ta);

		vp.add(new HTML("Название"));
		
		tb = new TextBox();
		tb.setName("name");
		tb.setWidth("100%");
		vp.add(tb);

		vp.add(new HTML("Оригинальное название"));
		
		tb = new TextBox();
		tb.setName("originalName");
		tb.setWidth("100%");
		vp.add(tb);

		vp.add(new HTML("Версия"));
		
		tb = new TextBox();
		tb.setName("version");
		tb.setWidth("100%");
		vp.add(tb);

		vp.add(new HTML("Год"));
		
		tb = new TextBox();
		tb.setName("year");
		tb.setWidth("100%");
		vp.add(tb);

		vp.add(new HTML("Формат"));
		
		tb = new TextBox();
		tb.setName("format");
		tb.setWidth("100%");
		vp.add(tb);

		vp.add(new HTML("Язык"));
		
		tb = new TextBox();
		tb.setName("language");
		tb.setWidth("100%");
		vp.add(tb);

		vp.add(new HTML("Размер"));
		
		tb = new TextBox();
		tb.setName("size");
		tb.setWidth("100%");
		vp.add(tb);

		// TODO screens

		vp.add(new HTML("Ссылки"));

		ta = new TextArea();
		ta.setName("fileLinks");
		ta.setWidth("100%");
		ta.setHeight("20em");
		vp.add(ta);
		
		vp.add(new HTML("Тэги"));
		
		tb = new TextBox();
		tb.setName("tags");
		tb.setWidth("100%");
		vp.add(tb);

		vp.add(new HTML("Разработчик"));

		tb = new TextBox();
		tb.setName("developer");
		tb.setWidth("100%");
		vp.add(tb);

		vp.add(new HTML("ОС"));

		tb = new TextBox();
		tb.setName("os");
		tb.setWidth("100%");
		vp.add(tb);

		vp.add(new HTML("Платформа"));

		tb = new TextBox();
		tb.setName("platform");
		tb.setWidth("100%");
		vp.add(tb);
		
		CheckBox cb = new CheckBox("free");
		cb.setName("free");
		vp.add(cb);
		
		cb = new CheckBox("crack");
		cb.setName("crack");
		vp.add(cb);

		// Wrap the content in a DecoratorPanel
		// DecoratorPanel decPanel = new DecoratorPanel();
		// decPanel.setWidget(layout);
		// return decPanel;
		//return layout;
		
		return vp;
	}

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
		RootPanel.get().add(splitPanel);
		splitPanel.setSize("100%", "100%");
		splitPanel.setSplitPosition("30%");
		splitPanel.add(new HTML("<b>asdfas</b><br>fasd"));
		//splitPanel.add(new HTML("<b>asdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdf</b>"));
		
		splitPanel.add(createPostWidget());
	}
}
