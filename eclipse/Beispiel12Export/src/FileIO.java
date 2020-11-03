import java.io.*;

public class FileIO {
	
	// 1. einfache Klassenmethode zum 
	//    zeilenweisen Einlesen einer Textdatei 
	//    in einen String
	public static String read(String fname){
		String s;
		String result="";
		try {
			BufferedReader in = new BufferedReader(
					    new FileReader(fname));
			while((s = in.readLine())!= null){
				result += s;
				result += '\n';
			}
			in.close();
		}
		catch(Exception e)
		{
			System.out.println("read Exception " + e);
		}
		return result;
	} // end method read()
	
	// 2. einfache Klassenmethode zum Schreiben eines Strings
	// auf eine Textdatei. Der String kann mehrere Zeilen
	// enthalten
	public static void write(String fname, String text){
		try {
			BufferedWriter out = new BufferedWriter (
							new FileWriter(fname));
			out.write(text);
			out.close();
		}
		
		catch(Exception e)
		{
			System.out.println("read Exception " + e);
		}
	}// end method write()
} // end class FileIO
