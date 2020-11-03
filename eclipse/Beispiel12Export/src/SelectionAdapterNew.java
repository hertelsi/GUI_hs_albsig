import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

public class SelectionAdapterNew 
          extends SelectionAdapter {

  private Text text;
  
  // Die aufrufende Klasse reicht hier
  // die Referenz auf ihr Textfeld
  // hinein.
  public SelectionAdapterNew(Text text) {
    this.text = text;
  } // end constructor

  // Reaktionsmethode leert
  // das Textfeld
  public void widgetSelected(
      SelectionEvent e){
	Shell shell = (Shell)text.getParent();
    // MessageBox erzeugen
	MessageBox msg = new MessageBox(shell,SWT.ICON_QUESTION |
    		SWT.YES | SWT.NO | SWT.CANCEL | SWT.WRAP);
    msg.setMessage("Wollen Sie auch wirklich wirklich???????");
    int result = msg.open();
    switch(result) {
    case SWT.YES: text.setText(""); break;
    case SWT.NO: break;
    case SWT.CANCEL: break;
    }
    
    
  } // end method widgetSelected()
} // end class SelectionAdapterNew
