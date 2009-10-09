package migool.op.client;

import static migool.op.client.GWTClientUtil.*;

import java.util.List;

import migool.op.client.serializable.PostSerializable;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;

/**
 * 
 * @author Denis Migol
 * 
 */
public final class PostWidget {
	private PostWidget() {
	}

	private static final String CUT = "[cut]";

	public static Widget create(final PostServiceAsync postService) {
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

		final FormPanel form = new FormPanel();
		vp.add(form);
		form.setAction("/upload");
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);

		final VerticalPanel fvp = new VerticalPanel();
		form.add(fvp);
		fvp.setWidth("100%");
		final FileUpload image = new FileUpload();
		image.setName("image");
		image.setWidth("100%");
		fvp.add(image);
		
		final Button upload = new Button("upload", new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				form.submit();
			}
		});
		fvp.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		fvp.add(upload);
		//vp.add(image);
		form.addSubmitHandler(new SubmitHandler() {
			@Override
			public void onSubmit(SubmitEvent event) {
				// TODO Auto-generated method stub
			}
		});
		form.addSubmitCompleteHandler(new SubmitCompleteHandler() {
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				DialogBox db = new DialogBox();
				db.setText(event.getResults());
				db.center();
				db.show();
				System.out.println(event.getResults());
				Image image = new Image("/upload");
				fvp.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
				fvp.add(image);
				
				upload.setEnabled(false);
			}
		});

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

				// public Image image; // TODO
				post.image = image.getFilename();

				String storyText = story.getText();
				if (!"".equals(storyText)) {
					int index = storyText.indexOf(CUT);
					int length = CUT.length();
					post.begStory = storyText.substring(0, index);
					post.endStory = storyText.substring(index + length, storyText.length());
				}

				post.name = name.getText();
				post.originalName = originalName.getText();
				post.version = version.getText();
				post.year = year.getText();
				post.format = format.getText();
				post.language = language.getText();
				post.size = size.getText();
				post.fileLinks = fileLinks.getText();
				post.tags = tags.getText();

				post.developer = developer.getText();
				post.os = os.getText();
				post.platform = platform.getText();
				post.free = free.getValue();
				post.crack = crack.getValue();

				postService.setPost(post, new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
					}

					@Override
					public void onFailure(Throwable caught) {
					}
				});
			}
		};
		Button post = new Button("отправить", postHandler);
		vp.add(post);

		return vp;
	}
}
