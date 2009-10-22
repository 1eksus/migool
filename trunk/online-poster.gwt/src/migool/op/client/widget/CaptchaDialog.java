package migool.op.client.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * @author Denis Migol
 * 
 */
public class CaptchaDialog extends DialogBox {
	/**
	 * 
	 * @author Denis Migol
	 *
	 */
	public static abstract class Callback {
		private String result;

		/**
		 * 
		 * @param result
		 */
		public void setResult(String result) {
			this.result = result;
		}

		/**
		 * 
		 * @return
		 */
		public String getResult() {
			return result;
		}

		/**
		 * 
		 */
		public abstract void onResult();
	}

	private final String imageUrl;
	private final Callback callback;
	
	private final TextBox input = new TextBox();
	
	public CaptchaDialog(String imageUrl, Callback callback) {
		super(false, true);
		// super.setTitle("Title");
		super.setText("CAPTCHA");

		this.imageUrl = imageUrl;
		this.callback = callback;

		createBody();		
	}
	
	private void doResult(String result) {
		callback.setResult(result);
		callback.onResult();
		hide();
	}

	private void createBody() {
		VerticalPanel vp = new VerticalPanel();
		add(vp);
		vp.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		Image img = new Image(imageUrl);
		vp.add(img);
		
		//final TextBox input = new TextBox();
		input.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					doResult(input.getText());
				}
			}
		});
		vp.add(input);
		final Button button = new Button("OK");
		button.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				doResult(input.getText());
			}
		});
		vp.add(button);
		// Focus the cursor on the input when the dialog loads
		input.setFocus(true);
		input.selectAll();
		center();
	}
	
	@Override
	public void show() {
		super.show();
		input.setFocus(true);
		input.selectAll();		
	}
}
