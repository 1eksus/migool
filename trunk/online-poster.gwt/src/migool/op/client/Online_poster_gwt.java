package migool.op.client;

import java.util.ArrayList;
import java.util.List;

import migool.op.client.serializable.PostSerializable;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.Hyperlink;
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

	private final PostServiceAsync postService = GWT.create(PostService.class);
	HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
	
	private static final String CUT = "[cut]";

	/**
	 * 
	 * @param lb
	 * @return
	 */
	private List<String> getSelectedItemsText(ListBox lb) {
		List<String> ret = new ArrayList<String>();
		int begin = lb.getSelectedIndex();
		if (begin > -1) {
			int size = lb.getItemCount();
			for (int i = begin; i < size; i++) {
				if (lb.isItemSelected(i)) {
					ret.add(lb.getItemText(i));
				}
			}
		}
		return ret;
	}

	/**
	 * 
	 * @return
	 */
	private Widget createPostWidget() {
		final VerticalPanel vp = new VerticalPanel();
		vp.setWidth("100%");

		vp.add(new HTML("Заголовок"));

		final TextBox title = new TextBox();
		title.setWidth("100%");
		vp.add(title);

		vp.add(new HTML("URL"));

		final TextBox url = new TextBox();
		url.setWidth("100%");
		vp.add(url);

		vp.add(new HTML("Категории"));

		final ListBox cats = new ListBox(true);
		postService.getCategories(new AsyncCallback<List<String>>() {

			@Override
			public void onSuccess(List<String> result) {
				for (String cat : result) {
					cats.addItem(cat);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
		vp.add(cats);

		vp.add(new HTML("Картинка"));

		final FileUpload image = new FileUpload();
		image.setWidth("100%");
		vp.add(image);

		vp.add(new HTML("Текст новости"));

		final TextArea story = new TextArea();
		story.setWidth("100%");
		story.setHeight("20em");
		vp.add(story);

		vp.add(new HTML("Название"));

		final TextBox name = new TextBox();
		name.setWidth("100%");
		vp.add(name);

		vp.add(new HTML("Оригинальное название"));

		final TextBox originalName = new TextBox();
		originalName.setWidth("100%");
		vp.add(originalName);

		vp.add(new HTML("Версия"));

		final TextBox version = new TextBox();
		version.setWidth("100%");
		vp.add(version);

		vp.add(new HTML("Год"));

		final TextBox year = new TextBox();
		year.setWidth("100%");
		vp.add(year);

		vp.add(new HTML("Формат"));

		final TextBox format = new TextBox();
		format.setWidth("100%");
		vp.add(format);

		vp.add(new HTML("Язык"));

		final TextBox language = new TextBox();
		language.setWidth("100%");
		vp.add(language);

		vp.add(new HTML("Размер"));

		final TextBox size = new TextBox();
		size.setWidth("100%");
		vp.add(size);

		// TODO screens

		vp.add(new HTML("Ссылки"));

		final TextArea fileLinks = new TextArea();
		fileLinks.setWidth("100%");
		fileLinks.setHeight("20em");
		vp.add(fileLinks);

		vp.add(new HTML("Тэги"));

		final TextBox tags = new TextBox();
		tags.setWidth("100%");
		vp.add(tags);

		vp.add(new HTML("Разработчик"));

		final TextBox developer = new TextBox();
		developer.setWidth("100%");
		vp.add(developer);

		vp.add(new HTML("ОС"));

		final TextBox os = new TextBox();
		os.setWidth("100%");
		vp.add(os);

		vp.add(new HTML("Платформа"));

		final TextBox platform = new TextBox();
		platform.setWidth("100%");
		vp.add(platform);

		final CheckBox free = new CheckBox("free");
		vp.add(free);

		final CheckBox crack = new CheckBox("crack");
		vp.add(crack);

		ClickHandler postHandler = new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				PostSerializable post = new PostSerializable();

				// TODO
				post.title = title.getText();
				post.url = url.getText();
				post.categories = getSelectedItemsText(cats);
				//public Image image; // TODO
				
				String storyText = story.getText();
				int index = storyText.indexOf(CUT);
				int length = CUT.length();
				post.begStory = storyText.substring(0, index);
				post.endStory = storyText.substring(index + length, storyText.length());

				DialogBox db = new DialogBox(true);
				db.setModal(true);
				db.center();
				db.show();
			}
		};
		Button post = new Button("отправить", postHandler);
		vp.add(post);

		return vp;
	}

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
				splitPanel.add(createPostWidget());
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
		splitPanel.add(createPostWidget());
	}
}
