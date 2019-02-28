import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogInfo implements Comparable<LogInfo> {
	String start;//시작시간
	String id;//esb_tran_id
	int length;//content-length
	String call;//galileo call time
	String before;//before marshalling
	String marshalling;//marshalling
	String galileo;//invoking galileo
	String cmmmod;//unmarshalling and send
	String end;//종료시간
	long time;//소요시간 = 종료시간 - 시작시간
	
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getCall() {
		return call;
	}
	public void setCall(String call) {
		this.call = call;
	}
	public String getBefore() {
		return before;
	}
	public void setBefore(String before) {
		this.before = before;
	}
	public String getMarshalling() {
		return marshalling;
	}
	public void setMarshalling(String marshalling) {
		this.marshalling = marshalling;
	}
	public String getGalileo() {
		return galileo;
	}
	public void setGalileo(String galileo) {
		this.galileo = galileo;
	}
	public String getCmmmod() {
		return cmmmod;
	}
	public void setCmmmod(String cmmmod) {
		this.cmmmod = cmmmod;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	
	//start와 end에 값이 들어오면 date로 변환해 소요시간을 계산함
	public void parseDate() {
		SimpleDateFormat sd = new SimpleDateFormat("yy.MM.dd HH:mm:ss");
		try {
			Date st = sd.parse(start);
			Date ed = sd.parse(end);
			time = ed.getTime()-st.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}	
	}
	
	//null check
	public boolean checkNull() {
		if(start==null||start.equals(null)) {
			return false;
		}
		if(id==null||id.equals(null)) {
			return false;
		}
		if(length==0) {
			return false;
		}
		if(call==null||call.equals(null)) {
			return false;
		}
		if(before==null||before.equals(null)) {
			return false;
		}
		if(marshalling==null||marshalling.equals(null)) {
			return false;
		}
		if(galileo==null||galileo.equals(null)) {
			return false;
		}
		if(cmmmod==null||cmmmod.equals(null)) {
			return false;
		}
		if(end==null||end.equals(null)) {
			return false;
		}
		return true;
	}
	
	//정해진 포맷대로 문자열을 출력하기 위함
	@Override
	public String toString() {
		return start + ", " + end + ", " + id + ", " + length + ", " + call + ", " + before
				+ ", " + marshalling + ", " + galileo + ", " + cmmmod;
	}
	
	//시작시간을 기준으로 시간순 정렬
	@Override
	public int compareTo(LogInfo loginfo) {
		return this.start.compareTo(loginfo.getStart());
	}
}
