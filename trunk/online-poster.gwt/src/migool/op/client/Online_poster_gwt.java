package migool.op.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
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

	private final CategoryServiceAsync categoryService = GWT.create(CategoryService.class);
	ListBox lb = new ListBox(true);

	/**
	 * 
	 * @return
	 */
	private Widget createPostWidget() {
		VerticalPanel vp = new VerticalPanel();
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

		lb.setName("cats");
		categoryService.getCategories(new AsyncCallback<List<String>>() {

			@Override
			public void onSuccess(List<String> result) {
				for (String cat : result) {
					lb.addItem(cat);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
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

		splitPanel.add(createPostWidget());
	}
}
