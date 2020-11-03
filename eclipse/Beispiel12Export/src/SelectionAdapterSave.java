import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SelectionAdapterSave 
          extends SelectionAdapter {
  
  // Shell des Editors
  private Shell parent;
  
  // Textfeld des Editors
  private Text text;
  
  public SelectionAdapterSave(
         Shell parent, Text text) {
    this.parent = parent;
    this.text = text;
  }
  
  public void widgetSelected(
          final SelectionEvent e) {
    // FileDialog zum Speichern einer
    // Datei erzeugen
    FileDialog f = 
          new FileDialog(parent, SWT.SAVE);
    
    // FileDialog aufklappen
    // --> seine open()-Methode liefert
    // den selektierten Dateinamen als
    // String zurueck
    String fname = f.open();
    
    // Inhalt des Textfeldes auf
    // die selektierte Datei
    // schreiben
    if(fname != null) {
      FileIO.write(fname, 
          text.getText());
    }
    
  } // end method widgetSelected()
} // end class SelectionAdapterSave
