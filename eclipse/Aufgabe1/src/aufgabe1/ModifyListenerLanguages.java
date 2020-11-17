package aufgabe1;
import java.util.Locale;
import java.util.ResourceBundle;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.MenuItem;


public class ModifyListenerLanguages implements ModifyListener {
	
	private ResourceBundle messages;
	private MenuItem fileNewItem; 
	private MenuItem fileOpenItem;
	private MenuItem fileSaveItem;
	private MenuItem fileQuitItem;
	private MenuItem fileTitle;
	private MenuItem editTitle;
	private MenuItem editColorItem;
	private MenuItem contextFileNewItem;
	private MenuItem contextFileSaveItem;


	public ModifyListenerLanguages(MenuItem fileNewItem, MenuItem fileOpenItem,
			MenuItem fileSaveItem, MenuItem fileQuitItem, MenuItem fileTitle, MenuItem editTitle,
			MenuItem editColorItem, MenuItem contextFileSaveItem, MenuItem contextFileNewItem) {
		this.fileNewItem = fileNewItem;
		this.fileOpenItem = fileOpenItem;
		this.fileSaveItem = fileSaveItem;
		this.fileQuitItem = fileQuitItem;
		this.fileTitle = fileTitle;
		this.editTitle = editTitle;
		this.editColorItem = editColorItem;
		this.contextFileNewItem = contextFileNewItem;
		this.contextFileSaveItem = contextFileSaveItem;
	}

	@Override
	public void modifyText(ModifyEvent e) {
		Combo source = (Combo) e.getSource();
		String language = source.getText();
		setText(language);
	}
	
	public void setText(String language) {
		changeLanguage(language);
		fileNewItem.setText(messages.getString("new"));
		fileOpenItem.setText(messages.getString("open"));
		fileSaveItem.setText(messages.getString("save"));
		fileQuitItem.setText(messages.getString("quit"));
		fileTitle.setText(messages.getString("file"));
		editTitle.setText(messages.getString("edit"));
		editColorItem.setText(messages.getString("textColor"));
		contextFileNewItem.setText(messages.getString("new"));
		contextFileSaveItem.setText(messages.getString("save"));
	}
	
	private void changeLanguage(String language) {
		Locale locale = null;
		switch(language) {
		case "Deutsch":
			locale = new Locale("de","DE");
			break;
		case "English":
			locale = new Locale("en","EN");
			break;
		}
		if (locale != null) {
			messages = ResourceBundle.getBundle("MessageBundle", locale);
		}
	}

}
