import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SelectionAdapterInputDialog 
  extends SelectionAdapter {
  
  // Parent-Shell des Editors
	private Shell parent;
	
	// Textfeld des Editors
	private Text text;
	
	public SelectionAdapterInputDialog(
	    Shell parent, Text text)
	{
		this.parent = parent;
		this.text = text;
	} // end constructor
	
	public void widgetSelected(final SelectionEvent e)
	{
	  // Dialogfenster erzeugen.
	  // Dialogfenster soll modal angezeigt
	  // werden.
	  InputDialog in = 
        new InputDialog(
            parent,SWT.APPLICATION_MODAL);
	  
	  // Dialogfenster aufklappen
    String s = in.open();
    
    if(s != null) {
      // Eingetippten Text ins Editor-
      // feld einblenden
      text.setText(s);
    }  
	} // end method widgetSelected()
} // end class SelectionAdapterInputDialog
