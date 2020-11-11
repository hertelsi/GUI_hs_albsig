import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SelectionAdapterQuit extends SelectionAdapter {
  
  private Shell shell;
  
  public SelectionAdapterQuit(Shell shell) {
	  this.shell = shell;
  }
  
  public void widgetSelected(final SelectionEvent e) {
	  MessageBox msg = new MessageBox(shell,SWT.ICON_QUESTION | SWT.YES | SWT.NO | SWT.WRAP);
		    
	  msg.setMessage("Do you really want to quit?");
	  int retval = msg.open();
	  switch(retval) {
	  	case SWT.YES: shell.dispose(); break;
	    case SWT.NO: break;
	  } 
  }
}
