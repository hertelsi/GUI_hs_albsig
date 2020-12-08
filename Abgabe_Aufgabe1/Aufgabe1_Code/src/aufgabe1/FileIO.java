package aufgabe1;
import java.io.*;

public class FileIO {
	
	public static String read(String fname){
		String s;
		String result="";
		try {
			BufferedReader in = new BufferedReader(new FileReader(fname));
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
	} 
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
	}
}
