package aufgabe1;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

public class SelectionAdapterNew extends SelectionAdapter {

  

  public SelectionAdapterNew() {
  } 

  public void widgetSelected(SelectionEvent e){
	  Aufgabe1 newWindow = Aufgabe1.createNewWindow();
	  newWindow.open();
    }
    
  } 
