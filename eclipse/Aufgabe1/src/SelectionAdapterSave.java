

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SelectionAdapterSave extends SelectionAdapter {
  
  private Shell parent;
  private Text text;
  
  public SelectionAdapterSave(
         Shell parent, Text text) {
    this.parent = parent;
    this.text = text;
  }
  
  public void widgetSelected(
          final SelectionEvent e) {
    FileDialog f = new FileDialog(parent, SWT.SAVE);
    String fname = f.open();
    if(fname != null) {
    	RGB color = text.getForeground().getRGB();
    	String colorStr = color.red + "," + color.green + "," + color.blue;
    	String newText = "//" + colorStr + "\n" + text.getText();
    	FileIO.write(fname,newText);
    }
  }
}
