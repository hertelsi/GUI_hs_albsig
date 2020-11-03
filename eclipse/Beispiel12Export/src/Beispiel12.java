import java.io.IOException;
import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class Beispiel12 {
  
  //Menue-Balken
  private Menu menuBar;     
  
  //Titel des File-Menues
  private MenuItem fileTitle;
  
  //File-Menue
  private Menu fileMenu;
  
  // Titel des Help-Menues
  private MenuItem helpTitle;
  
  // Help-Menue
  private Menu helpMenu;
  
  // Menue-Optionen der beiden 
  // Pulldown-Menues
  private MenuItem fileNewItem; 
  private MenuItem fileOpenItem;
  private MenuItem fileSaveItem;
  private MenuItem helpHelpItem;
  
  // Kontext-Menu im Textfeld
  // (bekommt keinen Menu-Titel)
  private Menu contextMenue;
  
  // Menu-Items fuer Kontext-Menu
  private MenuItem contextFileNewItem;
  private MenuItem contextFileSaveItem;
  
  //Textfeld zum Editieren
  private Text textField;
  
  // ToolBar
  private ToolBar toolbar;
  private ToolItem tiNew;
  private ToolItem tiSave;
  
  private Shell shell;
  private Display display;
  
  // 1. Shell erzeugen
  private void createShell(){

    shell = new Shell(display);
    
    // Sobald ToolBar oder CoolBar im Spiel:
    // 1-spaltiges GridLayout statt FillLayout
    shell.setLayout(
        new GridLayout(1,true));
  } // end method createShell()
  
  // 2. Display erzeugen
  private void createDisplay()
  {
    display = new Display();
  } // end method createDisplay()
  
  // 3. Menues einrichten
  private void createMenus() {
    // MenuBar erstellen
	menuBar = new Menu(shell,SWT.BAR);
	
	// MenuBar einhaengen
	shell.setMenuBar(menuBar);
	
	// MenuTitel erzeugen
	fileTitle = new MenuItem(menuBar,SWT.CASCADE);
	fileTitle.setText("&File");
	helpTitle = new MenuItem(menuBar,SWT.CASCADE);
	helpTitle.setText("&Heeeelllp!");
	
	// Menues aufsetzen
	fileMenu = new Menu(shell,SWT.DROP_DOWN);
	// Menu mit dem Menu-Titel verknuepfen
	fileTitle.setMenu(fileMenu);
	helpMenu = new Menu(shell,SWT.DROP_DOWN);
	helpTitle.setMenu(helpMenu);
	
	// Items erzeugen und in die Menues einsetzen
	fileNewItem = new MenuItem(fileMenu,SWT.PUSH);
	fileNewItem.setText("&New\tCtrl+N");
	fileNewItem.setAccelerator(SWT.CTRL + 'N');
	//fileNewItem.addSelectionListener(new SelectionAdapterNew(textField));
	
	fileOpenItem = new MenuItem(fileMenu, SWT.PUSH);
	fileOpenItem.setText("&Open...");
	

	fileSaveItem = new MenuItem(fileMenu,SWT.PUSH);
	fileSaveItem.setText("&Save...");
	
	helpHelpItem = new MenuItem(helpMenu,SWT.PUSH);
	helpHelpItem.setText("&Help");
    
  } // end method createMenus()
  
  //4. Textfeld mit Kontextmenue
  // erzeugen 
  private void createText(){
	  // Textfeld erzeugen
	  textField = new Text(shell,SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
	  // Layout-Daten fuer das 1-spaltige GridLayout
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
    
    
  }// end method createText()
  public void createToolBar() {
	 toolbar = new ToolBar(shell,SWT.FLAT);
	 // GridDaten fuer ToolBar: Soll in x-Richtung Zelle voll ausfuellen,
	 // in y-Richtung oben kleben bleiben (BEGINNING), in x-Richtung mitwachsen (true),
	 // in y-Richtung nicht mitwachsen (false) und in x/y-Richtung 1 Zelle belegen
	 GridData gData = new GridData(SWT.FILL,SWT.BEGINNING,true,false,1,1);
	 toolbar.setLayoutData(gData);
	 tiNew = new ToolItem(toolbar,SWT.PUSH);
	 tiSave = new ToolItem(toolbar,SWT.PUSH);
	 try {
		InputStream s1= 
					Button.class.getResource("/new_wiz.png").openStream();
		InputStream s2= 
				Button.class.getResource("/save_edit.png").openStream();
		 Image iNew = new Image(display,s1);
		 Image iSave = new Image (display,s2);
		 tiNew.setImage(iNew);
		 tiSave.setImage(iSave);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	 
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
  public Beispiel12() {
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

