import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LogDate {
	Map<String,LogFormat> map = new HashMap<String,LogFormat>();
	
	public void setLogdata(LogInfo li) {//�ð�(��)�� �������� �ִ밪,�ּҰ�,��հ��� ����ϴ� �޼ҵ�
		String time =li.getStart().substring(0,14);//��¥���Ŀ��� �� �κ��� �߶󳽴�.
		try {
			if(map.containsKey(time)) {//������ ��Ƶ״� �����͸� ����
				LogFormat lf = map.get(time);
				lf.setTime_sum(lf.getTime_sum()+li.getTime());//�ð� �հ�
				lf.setSize_sum(lf.getSize_sum()+li.getLength());//������ �հ�
				
				//�ҷ��� ���� map�� ����Ǿ��ִ� �����͸� ��
				if(lf.getTime_max()<li.getTime()) {//�ִ� �ð�
					lf.setTime_max(li.getTime());
				}
				if(lf.getTime_min()>li.getTime()) {//�ּ� �ð�
					lf.setTime_min(li.getTime());
				}
				if(lf.getSize_max()<li.getLength()) {//�ִ� ������
					lf.setSize_max(li.getLength());
				}
				if(lf.getSize_min()>li.getLength()) {//�ּ� ������
					lf.setSize_min(li.getLength());
				}
				
				lf.setCount(lf.getCount()+1);//ó�� �Ǽ�
				lf.setTime_avg(lf.getTime_sum()/lf.getCount());//��� �ð� = �ð� �հ� / ó���Ǽ�
				lf.setSize_avg(lf.getSize_sum()/lf.getCount());//��� ������ = ������ �հ� / ó���Ǽ�
			}else {
				LogFormat lf = new LogFormat();//map�� �ش��ϴ� �ð��밡 ������ �� ��ü�� �����ϰ� ���� �����Ѵ�.
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
	
	//��ü ����Ʈ�� ��ȯ
	public ArrayList<LogFormat> getList(){
		return new ArrayList<LogFormat>(map.values());
	}
}
