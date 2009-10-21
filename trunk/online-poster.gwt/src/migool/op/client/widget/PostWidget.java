package migool.op.client.widget;

import static migool.op.client.widget.GWTClientUtil.*;

import java.util.List;

import migool.op.client.PostServiceAsync;
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
public final class PostWidget extends VerticalPanel {
	
	private static final String SEND_TITLE = "отправить";

	private static final String CRACK_TITLE = "crack";

	private static final String FREE_TITLE = "free";

	private static final String PLATFORM_TITLE = "Платформа";

	private static final String OS_TITLE = "ОС";

	private static final String DEVELOPER_TITLE = "Разработчик";

	private static final String TAGS_TITLE = "Тэги";

	private static final String LINKS_TITLE = "Ссылки";

	private static final String SIZE_TITLE = "Размер";

	private static final String LANGUAGE_TITLE = "Язык";

	private static final String FORMAT_TITLE = "Формат";

	private static final String YEAR_TITLE = "Год";

	private static final String VERSION_TITLE = "Версия";

	private static final String ORIGINAL_NAME_TITLE = "Оригинальное название";

	private static final String NAME_TITLE = "Название";

	private static final String MESSAGE_TITLE = "Текст новости";

	private static final String UPLOAD_TITLE = "upload";

	private static final String _100 = "100%";

	private static final String IMAGE = "image";

	private static final String UPLOAD2 = "/upload";

	private static final String PICTURE_TITLE = "Картинка";

	private static final String CATEGORIES_TITLE = "Категории";

	private static final String URL = "URL";

	public static final String TITLE_TITLE = "Заголовок";
	
	private static final String CUT = "[cut]";

	private final PostServiceAsync service;

	/**
	 * 
	 * @param postService
	 */
	public PostWidget(final PostServiceAsync postService) {
		super();

		service = postService;

		setWidth(_100);
	}

	public static Widget create(final PostServiceAsync postService) {
		final VerticalPanel vp = new VerticalPanel();
		vp.setWidth(_100);

		vp.add(new HTML(TITLE_TITLE));

		final TextBox title = new TextBox();
		title.setWidth(_100);
		vp.add(title);

		vp.add(new HTML(URL));

		final TextBox url = new TextBox();
		url.setWidth(_100);
		vp.add(url);

		vp.add(new HTML(CATEGORIES_TITLE));

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

		vp.add(new HTML(PICTURE_TITLE));

		final FormPanel form = new FormPanel();
		vp.add(form);
		form.setAction(UPLOAD2);
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);

		final VerticalPanel fvp = new VerticalPanel();
		form.add(fvp);
		fvp.setWidth(_100);
		final FileUpload image = new FileUpload();
		image.setName(IMAGE);
		image.setWidth(_100);
		fvp.add(image);
		
		final Button upload = new Button(UPLOAD_TITLE, new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
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
				Image image = new Image(UPLOAD2);
				fvp.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
				fvp.add(image);
				
				upload.setEnabled(false);
			}
		});

		vp.add(new HTML(MESSAGE_TITLE));

		final TextArea story = new TextArea();
		story.setWidth(_100);
		story.setHeight("20em");
		vp.add(story);

		vp.add(new HTML(NAME_TITLE));

		final TextBox name = new TextBox();
		name.setWidth(_100);
		vp.add(name);

		vp.add(new HTML(ORIGINAL_NAME_TITLE));

		final TextBox originalName = new TextBox();
		originalName.setWidth(_100);
		vp.add(originalName);

		vp.add(new HTML(VERSION_TITLE));

		final TextBox version = new TextBox();
		version.setWidth(_100);
		vp.add(version);

		vp.add(new HTML(YEAR_TITLE));

		final TextBox year = new TextBox();
		year.setWidth(_100);
		vp.add(year);

		vp.add(new HTML(FORMAT_TITLE));

		final TextBox format = new TextBox();
		format.setWidth(_100);
		vp.add(format);

		vp.add(new HTML(LANGUAGE_TITLE));

		final TextBox language = new TextBox();
		language.setWidth(_100);
		vp.add(language);

		vp.add(new HTML(SIZE_TITLE));

		final TextBox size = new TextBox();
		size.setWidth(_100);
		vp.add(size);

		// TODO screens

		vp.add(new HTML(LINKS_TITLE));

		final TextArea fileLinks = new TextArea();
		fileLinks.setWidth(_100);
		fileLinks.setHeight("20em");
		vp.add(fileLinks);

		vp.add(new HTML(TAGS_TITLE));

		final TextBox tags = new TextBox();
		tags.setWidth(_100);
		vp.add(tags);

		vp.add(new HTML(DEVELOPER_TITLE));

		final TextBox developer = new TextBox();
		developer.setWidth(_100);
		vp.add(developer);

		vp.add(new HTML(OS_TITLE));

		final TextBox os = new TextBox();
		os.setWidth(_100);
		vp.add(os);

		vp.add(new HTML(PLATFORM_TITLE));

		final TextBox platform = new TextBox();
		platform.setWidth(_100);
		vp.add(platform);

		final CheckBox free = new CheckBox(FREE_TITLE);
		vp.add(free);

		final CheckBox crack = new CheckBox(CRACK_TITLE);
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
		Button post = new Button(SEND_TITLE, postHandler);
		vp.add(post);

		return vp;
	}
}
