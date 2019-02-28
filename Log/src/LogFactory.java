import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogFactory {//map에 들어있는 객체에 데이터를 세팅하는 과정
	private Pattern pattern = null;
	private Matcher matcher = null;
	private String thread = null;
	private Map<String,LogInfo> map = new HashMap<String,LogInfo>();
	private ArrayList<LogInfo> array = new ArrayList<LogInfo>();
	
	private void getStart(String str) {
		pattern = Pattern.compile("(\\d{2}(?:.\\d{2}){2} \\d{2}(?:\\:\\d{2}){2}).*?(eclipse.galileo-bean-thread-\\d{8})");
		matcher = pattern.matcher(str);
		if(matcher.find()) {
			LogInfo li = new LogInfo();//로그가 시작되면 객체를 새로 생성한다.
			li.setStart(matcher.group(1));
			map.put(matcher.group(2), li);//생성한 객체에 데이터를 넣고 map에 추가한다. 
		}
	}
	
	private void getId(String str) {
		pattern = Pattern.compile("(eclipse.galileo-bean-thread-\\d{8}).*?(IF_\\d{4}_\\d{2}_\\w{8}(?:-\\w{4}){3}-\\w{12})");
		matcher = pattern.matcher(str);
		if(matcher.find()) {
			if(map.containsKey(matcher.group(1))) {//찾은 쓰레드가 map에 key값으로 저장되어있으면 해당 쓰레드에 들어있는 객체를 꺼내와 데이터를 추가한다.
				map.get(matcher.group(1)).setId(matcher.group(2));
			}
		}
	}
	
	private void getLength(String str) {
		pattern = Pattern.compile("(eclipse.galileo-bean-thread-\\d{8}).*?Content-Length:(\\d+)");
		matcher = pattern.matcher(str);
		if(matcher.find()) {
			if(map.containsKey(matcher.group(1))) {
				map.get(matcher.group(1)).setLength(Integer.parseInt(matcher.group(2)));
			}
		}
	}
	
	private void getCall(String str) {
		pattern = Pattern.compile("(eclipse.galileo-bean-thread-\\d{8}).*?#galileo call time:(\\d+)");
		matcher = pattern.matcher(str);
		if(matcher.find()) {
			if(map.containsKey(matcher.group(1))) {
				map.get(matcher.group(1)).setCall(matcher.group(2));
			}
		}
	}
	
	private void getWatch(String str) {
		pattern = Pattern.compile("(eclipse.galileo-bean-thread-\\d{8})");
		matcher = pattern.matcher(str);
		if(matcher.find()) {
			if(map.containsKey(matcher.group(1))) {
				thread = matcher.group(1);//같은 로그를 구분하기 위해 해당 쓰레드명을 변수에 추가
			}
		}
	}
	
	private void getBefore(String str) {
		pattern = Pattern.compile("(\\d{5})");
		matcher = pattern.matcher(str);
		if(matcher.find()) {
				map.get(thread).setBefore(matcher.group(1));
		}
	}
	
	private void getMarshalling(String str) {
		pattern = Pattern.compile("(\\d{5})");
		matcher = pattern.matcher(str);
		if(matcher.find()) {
				map.get(thread).setMarshalling(matcher.group(1));
		}
	}
	
	private void getInvoking(String str) {
		pattern = Pattern.compile("(\\d{5})");
		matcher = pattern.matcher(str);
		if(matcher.find()) {
				map.get(thread).setGalileo(matcher.group(1));
		}
	}
	
	private void getUnmarshalling(String str) {
		pattern = Pattern.compile("(\\d{5})");
		matcher = pattern.matcher(str);
		if(matcher.find()) {
				map.get(thread).setCmmmod(matcher.group(1));
				thread = null;
		}
	}
	
	private void getEnd(String str) {
		pattern = Pattern.compile("(\\d{2}(?:.\\d{2}){2} \\d{2}(?:\\:\\d{2}){2}).*?(eclipse.galileo-bean-thread-\\d{8})");
		matcher = pattern.matcher(str);
		if(matcher.find()) {
			if(map.containsKey(matcher.group(2))) {
				map.get(matcher.group(2)).setEnd(matcher.group(1));
				map.get(matcher.group(2)).parseDate();
				array.add(map.get(matcher.group(2)));//분석이 끝난 객체를 array에  추가한다.
			}
		}
	}
	
	//데이터가 저장된 array를 리턴.
	public ArrayList<LogInfo> getList(){
		return array;
	}

	//str에 들어간 내용에 따라 다른 로직을 실행시킨다.
	public boolean getInstance(String str) {
		if(str.contains("##galileo_bean start.")) {
			getStart(str);
		}else if(str.contains("ESB_TRAN_ID :")) {
			getId(str);
		}else if(str.contains("Content-Length:")) {
			getLength(str);
		}else if(str.contains("#galileo call time:")) {
			getCall(str);
		}else if(str.contains("StopWatch")) {
			getWatch(str);
			//thread에 값이 없으면 StopWatch를 끝낸 것으로 판단하고 true를 반환한다.
			if(thread==null||thread.equals(null))
				return true;
			else {
				return false;//false를 보내면 호출한 측에서 flag 값을 저장하고 메소드 running을 실헹한다.
			}
		}else if(str.contains("##galileo_bean end.")) {
			getEnd(str);
		}
		return true;
	}

	//각 소요시간을 얻어오는 메소드
	public boolean running(String str) {
		if(str.contains("1. Before Marshalling")) {
			getBefore(str);
		}else if(str.contains("2. Marshalling")) {
			getMarshalling(str);
		}else if(str.contains("3. Invoking galileo")) {
			getInvoking(str);
		}else if(str.contains("4. Unmarshalling and Send to CmmMod Server")) {
			getUnmarshalling(str);
			return true;//4번까지 값을 저장하면 true를 반환하고 StopWahch를 끝마친다.
		}
		return false;
	}
}
