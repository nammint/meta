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
	LogFactory lf = new LogFactory();//첫 번째 결과파일을 담아낼 공간.
	LogDate ld = new LogDate();//두 번째 결과파일을 담아낼 공간.

	public LogReader(String path) {
		this.path = path;
	}
	
	//첫 번째 파일 데이터 저장
	public String readLog1() {
		Reader reader = null;
		BufferedReader br = null;
		StringBuilder string = new StringBuilder();
		String str = "";	
		try {
			reader = new FileReader(path);
			br = new BufferedReader(reader);
			//로그를 라인단위로 가져가 특정 키워드에 따라 flag값을 반환받는다.
			//로그 중 StopWatch가 검색되었을 때 false를 반환받고 그동안 다른 함수가 실행된다.
			while((str = br.readLine()) != null) {
				if(bl) {
					bl = lf.getInstance(str);
				}else {
					bl = lf.running(str);
				}
			}
			
			//리스트로 받은 map을 시간순으로 정렬하고  builder에 저장
			ArrayList<LogInfo> alli = lf.getList();
			Collections.sort(alli);
			for(LogInfo li:alli) {
				//객체에 비어있는 값은 없는지 null체크
				if(li.checkNull()) {
					string.append(li.toString()+"\n");
					ld.setLogdata(li);//첫 번재 파일의 결과값을 파라미터로 보내 두 번째 파일에 들어갈 데이터 값을 계산한다.
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(br==null)br.close();} catch (IOException e) {}
		}
		
		return string.toString();
	}
	
	//두 번째 파일 데이터 저장
	public String readLog2() {	
		StringBuilder builder = new StringBuilder();
		ArrayList<LogFormat> allf = ld.getList();//Log1을 하는 과정에서 저장해뒀던 데이터를 리스트로 받아온다.
		Collections.sort(allf);
		for(LogFormat lf:allf) {
			builder.append(lf.toString()+"\n");
		}
		
		return builder.toString();
	}
	
	//경로와 데이터를 받아와 파일출력
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
