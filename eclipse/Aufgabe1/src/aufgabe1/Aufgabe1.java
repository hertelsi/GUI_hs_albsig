package aufgabe1;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class Aufgabe1 {
  

  private Menu menuBar;     
  
  private Menu fileMenu;
  private Menu editMenu;
  
  private MenuItem fileNewItem; 
  private MenuItem fileOpenItem;
  private MenuItem fileSaveItem;
  private MenuItem fileQuitItem;
  private MenuItem fileTitle;
  private MenuItem editTitle;
  private MenuItem editColorItem;

  private Menu contextMenue;
  
  // Menu-Items fuer Kontext-Menu
  private MenuItem contextFileNewItem;
  private MenuItem contextFileSaveItem;
  
  //Textfeld zum Editieren
  private Text textField;
  
  // ToolBar
  private ToolBar toolbar;
  private ToolItem tiOpen;
  private ToolItem tiSave;
  private ToolItem tiLanguage;
  
  private Shell shell;
  private static Display display;
  
  private Combo languageCombo;
  private String[] languages = {"English","Deutsch"};
  
  private ModifyListenerLanguages modifyListenerLanguages;
  
  
  // 1. Shell erzeugen
  private void createShell(){
    shell = new Shell(display);
    shell.setLayout(
        new GridLayout(1,true));
  }
  private static void createDisplay()
  {
    display = new Display();
  }

  private void createMenus() {

	menuBar = new Menu(shell,SWT.BAR);
	
	shell.setMenuBar(menuBar);
	
	// MenuTitel erzeugen
	fileTitle = new MenuItem(menuBar,SWT.CASCADE);
	
	editTitle = new MenuItem(menuBar,SWT.CASCADE);
	
	// Menues aufsetzen
	fileMenu = new Menu(shell,SWT.DROP_DOWN);
	fileTitle.setMenu(fileMenu);
	editMenu = new Menu(shell,SWT.DROP_DOWN);
	editTitle.setMenu(editMenu);
	
	// Items erzeugen und in die Menues einsetzen
	fileNewItem = new MenuItem(fileMenu,SWT.PUSH);
	
	fileOpenItem = new MenuItem(fileMenu, SWT.PUSH);
	
	fileSaveItem = new MenuItem(fileMenu,SWT.PUSH);
	
	fileQuitItem = new MenuItem(fileMenu,SWT.PUSH);
	
	editColorItem = new MenuItem(editMenu,SWT.PUSH);
	
	
    
  } 

  private void createText(){
	  textField = new Text(shell,SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
	  GridData gData = new GridData(SWT.FILL,SWT.FILL,true,true,1,1);
	  textField.setLayoutData(gData);
	 
	  // Popup-Menue erzeugen
	  contextMenue = new Menu(shell,SWT.POP_UP);
	  // Mit Textfeld verknuepfen
	  textField.setMenu(contextMenue);
	  
	  // MenuItems erstellen
	  contextFileNewItem = new MenuItem(contextMenue,SWT.PUSH);
	  contextFileSaveItem = new MenuItem(contextMenue,SWT.PUSH);
    
    
  }
  private void createListeners() {
	  fileNewItem.addSelectionListener(new SelectionAdapterNew());
	  fileOpenItem.addSelectionListener(new SelectionAdapterOpen(shell,textField));
	  tiOpen.addSelectionListener(new SelectionAdapterOpen(shell, textField));
	  fileSaveItem.addSelectionListener(new SelectionAdapterSave(shell,textField));
	  contextFileNewItem.addSelectionListener(new SelectionAdapterNew());
	  contextFileSaveItem.addSelectionListener(new SelectionAdapterSave(shell,textField));
	  tiSave.addSelectionListener(new SelectionAdapterSave(shell,textField));
	  fileQuitItem.addSelectionListener(new SelectionAdapterQuit(shell));
	  editColorItem.addSelectionListener(new SelectionAdapterTextColor(shell,textField));
	  modifyListenerLanguages = new ModifyListenerLanguages(fileNewItem, fileOpenItem, fileSaveItem, fileQuitItem, fileTitle, editTitle, editColorItem, contextFileNewItem, contextFileSaveItem);
	  languageCombo.addModifyListener(modifyListenerLanguages);
  }
private void createToolBar() {
	 toolbar = new ToolBar(shell,SWT.FLAT);
	 GridData gData = new GridData(SWT.FILL,SWT.BEGINNING,true,false,1,1);
	 toolbar.setLayoutData(gData);
	 tiOpen = new ToolItem(toolbar,SWT.PUSH);
	 tiSave = new ToolItem(toolbar,SWT.PUSH);
	 try {
		InputStream s1 = Button.class.getResource("/open-folder.png").openStream();
		InputStream s2 = Button.class.getResource("/save_edit.png").openStream();
		 Image iNew = new Image(display,s1);
		 Image iSave = new Image (display,s2);
		 tiOpen.setImage(iNew);
		 tiSave.setImage(iSave);
	} catch (IOException e) {
		e.printStackTrace();
	}
	tiLanguage = new ToolItem(toolbar, SWT.SEPARATOR);
	languageCombo = new Combo(toolbar,SWT.READ_ONLY);
	for(String language: languages) {
		languageCombo.add(language);
	}
	languageCombo.pack();
	tiLanguage.setWidth(languageCombo.getSize().x);
	tiLanguage.setControl(languageCombo);
	languageCombo.select(0);
	
  }
  
  
  public static Aufgabe1 createNewWindow() {
	  if(display == null) {
		  createDisplay();
	  }
	  return new Aufgabe1();
  }
  
  public void setText(String text) {
	  this.textField.setText(text);
  }
  
  public void setColor(RGB color) {
	  this.textField.setForeground(new Color(color));
  }
  
  
  
  public Aufgabe1() {
    createShell();
    createMenus();
    createToolBar();
    createText();
    createListeners();
    modifyListenerLanguages.setText("English");
  }
  
  public void open(){
    shell.open();
    while(!shell.isDisposed()){
      if(!display.readAndDispatch()){
        display.sleep();
      }
    }
  }
}

