import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogFactory {//map�� ����ִ� ��ü�� �����͸� �����ϴ� ����
	private Pattern pattern = null;
	private Matcher matcher = null;
	private String thread = null;
	private Map<String,LogInfo> map = new HashMap<String,LogInfo>();
	private ArrayList<LogInfo> array = new ArrayList<LogInfo>();
	
	private void getStart(String str) {
		pattern = Pattern.compile("(\\d{2}(?:.\\d{2}){2} \\d{2}(?:\\:\\d{2}){2}).*?(eclipse.galileo-bean-thread-\\d{8})");
		matcher = pattern.matcher(str);
		if(matcher.find()) {
			LogInfo li = new LogInfo();//�αװ� ���۵Ǹ� ��ü�� ���� �����Ѵ�.
			li.setStart(matcher.group(1));
			map.put(matcher.group(2), li);//������ ��ü�� �����͸� �ְ� map�� �߰��Ѵ�. 
		}
	}
	
	private void getId(String str) {
		pattern = Pattern.compile("(eclipse.galileo-bean-thread-\\d{8}).*?(IF_\\d{4}_\\d{2}_\\w{8}(?:-\\w{4}){3}-\\w{12})");
		matcher = pattern.matcher(str);
		if(matcher.find()) {
			if(map.containsKey(matcher.group(1))) {//ã�� �����尡 map�� key������ ����Ǿ������� �ش� �����忡 ����ִ� ��ü�� ������ �����͸� �߰��Ѵ�.
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
				thread = matcher.group(1);//���� �α׸� �����ϱ� ���� �ش� ��������� ������ �߰�
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
				array.add(map.get(matcher.group(2)));//�м��� ���� ��ü�� array��  �߰��Ѵ�.
			}
		}
	}
	
	//�����Ͱ� ����� array�� ����.
	public ArrayList<LogInfo> getList(){
		return array;
	}

	//str�� �� ���뿡 ���� �ٸ� ������ �����Ų��.
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
			//thread�� ���� ������ StopWatch�� ���� ������ �Ǵ��ϰ� true�� ��ȯ�Ѵ�.
			if(thread==null||thread.equals(null))
				return true;
			else {
				return false;//false�� ������ ȣ���� ������ flag ���� �����ϰ� �޼ҵ� running�� �����Ѵ�.
			}
		}else if(str.contains("##galileo_bean end.")) {
			getEnd(str);
		}
		return true;
	}

	//�� �ҿ�ð��� ������ �޼ҵ�
	public boolean running(String str) {
		if(str.contains("1. Before Marshalling")) {
			getBefore(str);
		}else if(str.contains("2. Marshalling")) {
			getMarshalling(str);
		}else if(str.contains("3. Invoking galileo")) {
			getInvoking(str);
		}else if(str.contains("4. Unmarshalling and Send to CmmMod Server")) {
			getUnmarshalling(str);
			return true;//4������ ���� �����ϸ� true�� ��ȯ�ϰ� StopWahch�� ����ģ��.
		}
		return false;
	}
}
