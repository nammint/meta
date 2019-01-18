import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileRead implements Input{
	BufferedReader br = null;
	String path;
	
	public FileRead(String path) {
		this.path = path;
	}
	
	@Override
	public String read() {
		String str = "";
		String letters = "";
		try {
			ClassLoader cl = FileRead.class.getClassLoader();
			InputStream is = cl.getResourceAsStream(path);
			
			br = new BufferedReader(new InputStreamReader(is));
			while((str = br.readLine()) != null) {
				letters += str+"\n";	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {br.close();} catch (IOException e) {}
		}
		test(letters);
		return letters;
	}
	
	public void test(String letters) {
		System.out.println("입력하신 문장은\n"+letters);
	}
}
