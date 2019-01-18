import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FileWrite implements Output {
	
	Writer writer = null;
	String path;
	
	public FileWrite(String path) {
		this.path=path;
	}

	@Override
	public void write(int cntLetters) {
		try {
			writer = new FileWriter(path);
			writer.write("입력하신 단어의 수는 "+Integer.toString(cntLetters));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {writer.close();} catch (IOException e) {}
		}
	}

}
