import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SelectionAdapterTextColor extends SelectionAdapter {
  
  private Shell parent;
  private Text textField;
  
  public SelectionAdapterTextColor(Shell parent, Text textField) {
    this.parent = parent;
    this.textField = textField;
  }
  
  public void widgetSelected(final SelectionEvent e) {
	  
	  ColorDialog cd = new ColorDialog(parent);
	  RGB color = cd.open();
	  if(color!=null) {
		  this.textField.setForeground(new Color(color));
	  }
  }
}
