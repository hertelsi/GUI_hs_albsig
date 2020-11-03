import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SelectionAdapterOpen 
   extends SelectionAdapter {

  // Shell des Editors
  private Shell parent;
  
  // Textfeld des Editors
  private Text text;
  
  public SelectionAdapterOpen(
      Shell parent, Text text) {
    this.parent = parent;
    this.text = text;
  } // end constructor
  
  @Override
  public void widgetSelected(
       SelectionEvent e){
    // MessageBox erzeugen: Sie hat die
    // Buttons: Yes, No, Cancel
    // Sie hat ein Question-Icon
    // Der Message-Text wird umgebrochen,
    // wenn er zu lang ist.
    MessageBox msg = new MessageBox(parent,
        SWT.ICON_QUESTION | SWT.YES 
        | SWT.NO | SWT.CANCEL | SWT.WRAP);
    
    // Nachrichtentext fuer die MessageBox
    // setzen
    msg.setMessage(
        "Wollen Sie auch wirklich?");
    
    // MessageBox aufklappen: Die open()-
    // Methode liefert als int-Wert zurueck,
    // mit welchem PushButton sie zugeklappt
    // wurde
    int retval = msg.open();
    switch(retval) {
      case SWT.YES: openFileDialog(); break;
      case SWT.NO: break;
      case SWT.CANCEL: break;
    } 
  } // end method widgetSelected()
  
 //private Hilfsmethode zum
 // Oeffnen einer Datei
 private void openFileDialog()
 {
   // FileDialog zum Oeffnen einer
   // Datei erzeugen
   FileDialog f = 
       new FileDialog(parent, SWT.OPEN);
   
   // FileDialog aufklappen
   // --> seine open()-Methode liefert
   // den selektierten Dateinamen als
   // String zurueck
   String fname = f.open();
   
   // Inhalt aus der selektierten
   // Datei in String txt einlesen
   if(fname != null) {
     String txt = FileIO.read(fname);
     
     // String txt ins Textfeld
     // einblenden
     text.setText(txt);
   } 
 } // end method openFileDialog()
} // end class SelectionAdapterOpen
