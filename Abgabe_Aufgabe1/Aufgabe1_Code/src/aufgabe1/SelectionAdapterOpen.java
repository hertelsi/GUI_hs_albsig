package aufgabe1;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SelectionAdapterOpen 
   extends SelectionAdapter {

  private Shell parent;
  private Text text;
  
  public SelectionAdapterOpen(Shell parent, Text text) {
    this.parent = parent;
    this.text = text;
  }
  
  @Override
  public void widgetSelected(SelectionEvent e){
	  openFileDialog();
    } 
  
 private void openFileDialog()
 {
   FileDialog f = new FileDialog(parent, SWT.OPEN);
   
   String fname = f.open();
   
   
   
   if(fname != null) {
	 Aufgabe1 newWindow = Aufgabe1.createNewWindow();
	 String txt = FileIO.read(fname);
     try {
    	 String[] lines = txt.split("\n");
    	 String colorStr = lines[0].substring(2);
    	 String colorsStr[] = colorStr.split(",");
    	 ArrayList<Integer> colorsInt = new ArrayList(3);
    	 for(String s:colorsStr) {
    		 colorsInt.add(Integer.parseInt(s));
    	 }
    	 RGB color = new RGB(colorsInt.get(0),colorsInt.get(1), colorsInt.get(2));
    	 newWindow.setColor(color);
    	 txt = "";
    	 for(int i=1; i<lines.length; i++) {
    		 txt += lines[i];
    	 }
     }catch(Exception e){
    	 e.printStackTrace();
     }
    
     
     newWindow.setText(txt);
     newWindow.open();
   } 
 }
}
