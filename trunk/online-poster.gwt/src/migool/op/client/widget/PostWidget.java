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
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
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
	private static final String TITLE_TITLE = "Заголовок";

	private static final String CUT = "[cut]";
	
	private final PostServiceAsync service;
	
	private final TextBox title = new TextBox();
	private final TextBox url = new TextBox();
	private final ListBox cats = new ListBox(true);
	private String imageUrl;
	private final TextArea story = new TextArea();
	private final TextBox name = new TextBox();
	private final TextBox originalName = new TextBox();
	private final TextBox version = new TextBox();
	private final TextBox year = new TextBox();
	private final TextBox format = new TextBox();
	private final TextBox language = new TextBox();
	private final TextBox size = new TextBox();
	private final TextArea fileLinks = new TextArea();
	private final TextBox tags = new TextBox();
	private final TextBox developer = new TextBox();
	private final TextBox os = new TextBox();
	private final TextBox platform = new TextBox();
	private final CheckBox free = new CheckBox(FREE_TITLE);
	private final CheckBox crack = new CheckBox(CRACK_TITLE);

	/**
	 * 
	 * @param postService
	 */
	public PostWidget(final PostServiceAsync postService) {
		super();

		service = postService;

		createBody();
	}

	private void createBody() {
		setWidth(_100);

		add(new HTML(TITLE_TITLE));

		title.setWidth(_100);
		add(title);

		add(new HTML(URL));

		url.setWidth(_100);
		add(url);

		add(new HTML(CATEGORIES_TITLE));

		fillCategories(cats);
		add(cats);

		add(new HTML(PICTURE_TITLE));

		final FormPanel form = new FormPanel();
		add(form);
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
		final Image img = new Image("");
		img.setVisible(false);
		fvp.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		fvp.add(img);
		form.addSubmitCompleteHandler(new SubmitCompleteHandler() {
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				imageUrl = event.getResults();
				img.setVisible(true);
				System.out.println(imageUrl);
				img.setUrl(imageUrl);
				final CaptchaDialog cd = new CaptchaDialog(imageUrl, new CaptchaDialog.Callback() {
					@Override
					public void onResult() {
						System.out.println(getResult());
					}
				});
				cd.show();
			}
		});

		add(new HTML(MESSAGE_TITLE));

		story.setWidth(_100);
		story.setHeight("20em");
		add(story);

		add(new HTML(NAME_TITLE));

		name.setWidth(_100);
		add(name);

		add(new HTML(ORIGINAL_NAME_TITLE));

		originalName.setWidth(_100);
		add(originalName);

		add(new HTML(VERSION_TITLE));

		version.setWidth(_100);
		add(version);

		add(new HTML(YEAR_TITLE));

		year.setWidth(_100);
		add(year);

		add(new HTML(FORMAT_TITLE));

		format.setWidth(_100);
		add(format);

		add(new HTML(LANGUAGE_TITLE));

		language.setWidth(_100);
		add(language);

		add(new HTML(SIZE_TITLE));

		size.setWidth(_100);
		add(size);

		// TODO screens

		add(new HTML(LINKS_TITLE));

		fileLinks.setWidth(_100);
		fileLinks.setHeight("20em");
		add(fileLinks);

		add(new HTML(TAGS_TITLE));

		tags.setWidth(_100);
		add(tags);

		add(new HTML(DEVELOPER_TITLE));

		developer.setWidth(_100);
		add(developer);

		add(new HTML(OS_TITLE));

		os.setWidth(_100);
		add(os);

		add(new HTML(PLATFORM_TITLE));

		platform.setWidth(_100);
		add(platform);

		add(free);

		add(crack);

		Button post = new Button(SEND_TITLE, new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				service.setPost(getPost(), new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
					}

					@Override
					public void onFailure(Throwable caught) {
					}
				});
			}
		});
		add(post);
	}

	private void fillCategories(final ListBox cats) {
		service.getCategories(new AsyncCallback<List<String>>() {
			@Override
			public void onSuccess(List<String> result) {
				int size = result.size();
				for (int i = 0; i < size; i++) {
					cats.addItem(result.get(i));
				}
			}
			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}

	private PostSerializable getPost() {
		PostSerializable post = new PostSerializable();

		post.title = title.getText();
		post.url = url.getText();
		post.categories = getSelectedItemsText(cats);

		// TODO
		// public Image image; // TODO
		//post.image = image.getFilename();

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
		return post;
	}
}
