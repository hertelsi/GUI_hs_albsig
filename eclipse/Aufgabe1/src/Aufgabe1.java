import java.io.IOException;
import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
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
  private Display display;
  
  private Combo languageCombo;
  private String[] languages = {"Englisch","Deutsch"};
  
  // 1. Shell erzeugen
  private void createShell(){
    shell = new Shell(display);
    shell.setLayout(
        new GridLayout(1,true));
  }
  private void createDisplay()
  {
    display = new Display();
  }

  private void createMenus() {

	menuBar = new Menu(shell,SWT.BAR);
	
	shell.setMenuBar(menuBar);
	
	// MenuTitel erzeugen
	fileTitle = new MenuItem(menuBar,SWT.CASCADE);
	fileTitle.setText("&File");
	editTitle = new MenuItem(menuBar,SWT.CASCADE);
	editTitle.setText("&Edit");
	
	// Menues aufsetzen
	fileMenu = new Menu(shell,SWT.DROP_DOWN);
	fileTitle.setMenu(fileMenu);
	editMenu = new Menu(shell,SWT.DROP_DOWN);
	editTitle.setMenu(editMenu);
	
	// Items erzeugen und in die Menues einsetzen
	fileNewItem = new MenuItem(fileMenu,SWT.PUSH);
	fileNewItem.setText("&New");
	
	fileOpenItem = new MenuItem(fileMenu, SWT.PUSH);
	fileOpenItem.setText("&Open...");
	
	fileSaveItem = new MenuItem(fileMenu,SWT.PUSH);
	fileSaveItem.setText("&Save...");
	
	fileQuitItem = new MenuItem(fileMenu,SWT.PUSH);
	fileQuitItem.setText("&Quit");
	
	editColorItem = new MenuItem(editMenu,SWT.PUSH);
	editColorItem.setText("Text &Color");
    
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
	  contextFileNewItem.setText("New");
	  contextFileSaveItem = new MenuItem(contextMenue,SWT.PUSH);
	  contextFileSaveItem.setText("Save");
    
    
  }
  public void createToolBar() {
	 toolbar = new ToolBar(shell,SWT.FLAT);
	 // GridDaten fuer ToolBar: Soll in x-Richtung Zelle voll ausfuellen,
	 // in y-Richtung oben kleben bleiben (BEGINNING), in x-Richtung mitwachsen (true),
	 // in y-Richtung nicht mitwachsen (false) und in x/y-Richtung 1 Zelle belegen
	 GridData gData = new GridData(SWT.FILL,SWT.BEGINNING,true,false,1,1);
	 toolbar.setLayoutData(gData);
	 tiOpen = new ToolItem(toolbar,SWT.PUSH);
	 tiSave = new ToolItem(toolbar,SWT.PUSH);
	 try {
		InputStream s1= 
					Button.class.getResource("/new_wiz.png").openStream();
		InputStream s2= 
				Button.class.getResource("/save_edit.png").openStream();
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
	
  }
  
  
  private void createListeners() {
	  fileNewItem.addSelectionListener(
			  new SelectionAdapterNew(textField));
	  fileOpenItem.addSelectionListener(
			  new SelectionAdapterOpen(shell,textField));
	  fileSaveItem.addSelectionListener(
			  new SelectionAdapterSave(shell,textField));
	  contextFileNewItem.addSelectionListener(
			  new SelectionAdapterNew(textField));
	  contextFileSaveItem.addSelectionListener(
			  new SelectionAdapterSave(shell,textField));
  }
  
  // 5. Konstruktor
  public Aufgabe1() {
    createDisplay();
    createShell();
    createMenus();
    createToolBar();
    createText();
    createListeners();
  } // end Constructor
  
  // 6. EventLoop
  public void open(){ //wie immer ...
    shell.open();
    while(!shell.isDisposed()){
      if(!display.readAndDispatch()){
        display.sleep();
      }
    }
  } // end method open()
} // end class Beispiel11

