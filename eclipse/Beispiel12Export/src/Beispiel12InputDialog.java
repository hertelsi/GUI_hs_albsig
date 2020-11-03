import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

// Klasse ist frei nach Warner et. al.
//  S. 205 ff
public class Beispiel12InputDialog 
    extends Dialog {

  private String message;
  private String input;

  //1. Konstruktor
  public Beispiel12InputDialog(Shell parent, 
      int style) {
    super(parent, style);
    message = "Example Text:";  
  } // end constructor
  
  // 2. Erzeugt Layout, startet Eventloop
  //    gibt eingetippten String zurueck
  public String open(){
    // Shell wird nur intern verarbeitet:
    // getParent() liefert die Shell des
    // Hauptfensters
    // Shell des Dialogfensters ist Kind
    // der Shell des Hauptfensters
    Shell shellDialogWindow =
        new Shell(getParent());
    shellDialogWindow.setText("Input Dialog");
    
    // Inhalt der Shell wird erzeugt
    createContents(shellDialogWindow);
    shellDialogWindow.pack();
    
    // EventLoop wird gestartet:
    shellDialogWindow.open();
    
    // this.getParent() liefert die Shell des
    // Hauptfensters
    // getDisplay() liefert das Display des
    // Hauptfensters
    Display display = 
        getParent().getDisplay();
    // ACHTUNG: Das ist nun die eigene 
    // Event-Loop des Dialogfensters !!
    while(!shellDialogWindow.isDisposed()){
      if(!display.readAndDispatch()){
        display.sleep();
      }
    }
    
    // Achtung: An dieser Stelle ist die Shell
    // des Dialogfensters disposed!
    return input;
    
  } // end method open()
  
  // 3. private Hilfsmethode zum
  // Befuellen der Shell des Dialogfensters
  private void createContents(
      final Shell shellDialogWindow) {
    Label label;
    Button ok;
    Button cancel;
    final Text text;
    
    // Shell des Dialogfensters bekommt
    // 2-spaltiges GridLayout
    shellDialogWindow.setLayout(
        new GridLayout(2,true));
      
    // Label erzeugen 
    label = 
        new Label(shellDialogWindow, SWT.NONE);
    label.setText(message);
    GridData grid = new GridData();
    
    //Label belegt 2 Spalten
    grid.horizontalSpan = 2;
    label.setLayoutData(grid);

    // Textfeld erzeugen
    text = 
        new Text(shellDialogWindow, 
                 SWT.BORDER);
    grid = new GridData(
        GridData.FILL_HORIZONTAL);
    // Textfeld belegt 2 Spalten
    grid.horizontalSpan = 2;
    text.setLayoutData(grid);
    
    // Ok-Button erzeugen
    ok = new Button(
        shellDialogWindow, SWT.PUSH);
    ok.setText("Ok");
    grid = new GridData(
        GridData.FILL_HORIZONTAL);
    ok.setLayoutData(grid);
    ok.addSelectionListener(
        new SelectionAdapter() {
          
      public void widgetSelected(
          SelectionEvent e){
        // Wenn ok gedrueckt, uebernimm
        // Text aus Textfeld in interne
        // String-Variable!
        input = text.getText();
        
        // Zerstoere Shell des
        // Dialogfensters --> Klapp zu!
        shellDialogWindow.dispose();
      } // end method widgetSelected()
    }); // end Listener
    
    // Cancel-Button erzeugen
    cancel = 
        new Button(shellDialogWindow, 
                  SWT.PUSH);
    cancel.setText("Abbrechen");
    grid = new GridData(
        GridData.FILL_HORIZONTAL);
    cancel.setLayoutData(grid);
    
    cancel.addSelectionListener(
        new SelectionAdapter() {
      public void widgetSelected(
          SelectionEvent e){
        input = null;
        
        // Zerstoere Shell des
        // Dialogfensters --> Klapp zu!
        shellDialogWindow.dispose();
      }
    });
  }// end method createContents
}// end class Beispiel12InputDialog
