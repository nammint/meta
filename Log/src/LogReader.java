import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;

public class LogReader {
	String path;
	boolean bl = true;
	LogFactory lf = new LogFactory();//ù ��° ��������� ��Ƴ� ����.
	LogDate ld = new LogDate();//�� ��° ��������� ��Ƴ� ����.

	public LogReader(String path) {
		this.path = path;
	}
	
	//ù ��° ���� ������ ����
	public String readLog1() {
		Reader reader = null;
		BufferedReader br = null;
		StringBuilder string = new StringBuilder();
		String str = "";	
		try {
			reader = new FileReader(path);
			br = new BufferedReader(reader);
			//�α׸� ���δ����� ������ Ư�� Ű���忡 ���� flag���� ��ȯ�޴´�.
			//�α� �� StopWatch�� �˻��Ǿ��� �� false�� ��ȯ�ް� �׵��� �ٸ� �Լ��� ����ȴ�.
			while((str = br.readLine()) != null) {
				if(bl) {
					bl = lf.getInstance(str);
				}else {
					bl = lf.running(str);
				}
			}
			
			//����Ʈ�� ���� map�� �ð������� �����ϰ�  builder�� ����
			ArrayList<LogInfo> alli = lf.getList();
			Collections.sort(alli);
			for(LogInfo li:alli) {
				//��ü�� ����ִ� ���� ������ nullüũ
				if(li.checkNull()) {
					string.append(li.toString()+"\n");
					ld.setLogdata(li);//ù ���� ������ ������� �Ķ���ͷ� ���� �� ��° ���Ͽ� �� ������ ���� ����Ѵ�.
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(br==null)br.close();} catch (IOException e) {}
		}
		
		return string.toString();
	}
	
	//�� ��° ���� ������ ����
	public String readLog2() {	
		StringBuilder builder = new StringBuilder();
		ArrayList<LogFormat> allf = ld.getList();//Log1�� �ϴ� �������� �����ص״� �����͸� ����Ʈ�� �޾ƿ´�.
		Collections.sort(allf);
		for(LogFormat lf:allf) {
			builder.append(lf.toString()+"\n");
		}
		
		return builder.toString();
	}
	
	//��ο� �����͸� �޾ƿ� �������
	public void writeFile(String path,String log) {
		Writer writer = null;
		try {
			writer = new FileWriter(path);
			writer.write(log);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {writer.close();} catch (IOException e) {}
		}		
	}
	
}
