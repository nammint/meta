import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LogDate {
	Map<String,LogFormat> map = new HashMap<String,LogFormat>();
	
	public void setLogdata(LogInfo li) {//시간(분)을 기준으로 최대값,최소값,평균값을 계산하는 메소드
		String time =li.getStart().substring(0,14);//날짜형식에서 초 부분을 잘라낸다.
		try {
			if(map.containsKey(time)) {//기존에 담아뒀던 데이터를 셋팅
				LogFormat lf = map.get(time);
				lf.setTime_sum(lf.getTime_sum()+li.getTime());//시간 합계
				lf.setSize_sum(lf.getSize_sum()+li.getLength());//사이즈 합계
				
				//불러온 값과 map에 저장되어있는 데이터를 비교
				if(lf.getTime_max()<li.getTime()) {//최대 시간
					lf.setTime_max(li.getTime());
				}
				if(lf.getTime_min()>li.getTime()) {//최소 시간
					lf.setTime_min(li.getTime());
				}
				if(lf.getSize_max()<li.getLength()) {//최대 사이즈
					lf.setSize_max(li.getLength());
				}
				if(lf.getSize_min()>li.getLength()) {//최소 사이즈
					lf.setSize_min(li.getLength());
				}
				
				lf.setCount(lf.getCount()+1);//처리 건수
				lf.setTime_avg(lf.getTime_sum()/lf.getCount());//평균 시간 = 시간 합계 / 처리건수
				lf.setSize_avg(lf.getSize_sum()/lf.getCount());//평균 사이즈 = 사이즈 합계 / 처리건수
			}else {
				LogFormat lf = new LogFormat();//map에 해당하는 시간대가 없으면 새 객체를 생성하고 값을 설정한다.
				lf.setStart(time);
				lf.setTime_sum(li.getTime());
				lf.setTime_max(li.getTime());
				lf.setTime_min(li.getTime());
				lf.setSize_sum(li.getLength());
				lf.setSize_max(li.getLength());
				lf.setSize_min(li.getLength());
				lf.setCount(1);
				lf.setTime_avg(lf.getTime_sum()/lf.getCount());
				lf.setSize_avg(lf.getSize_sum()/lf.getCount());
				map.put(time,lf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//객체 리스트를 반환
	public ArrayList<LogFormat> getList(){
		return new ArrayList<LogFormat>(map.values());
	}
}
