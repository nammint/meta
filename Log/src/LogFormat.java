
public class LogFormat implements Comparable<LogFormat>{
	String start;//�ð�
	int count;//ó���Ǽ�
	long time_max;//�ִ� �ð�
	long time_min;//�ּ� �ð�
	long time_sum;//�ð� �հ�
	long time_avg;//��� �ҿ�ð� = �ð� �հ�/ó���Ǽ�
	int size_max;//�ִ� ������
	int size_min;//�ּ� ������
	int size_sum;//������ �հ�
	int size_avg;//��� ������ = ������ �հ�/ó���Ǽ�
	
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public long getTime_max() {
		return time_max;
	}
	public void setTime_max(long time_max) {
		this.time_max = time_max;
	}
	public long getTime_min() {
		return time_min;
	}
	public void setTime_min(long time_min) {
		this.time_min = time_min;
	}
	public long getTime_sum() {
		return time_sum;
	}
	public void setTime_sum(long time_sum) {
		this.time_sum = time_sum;
	}
	public long getTime_avg() {
		return time_avg;
	}
	public void setTime_avg(long time_avg) {
		this.time_avg = time_avg;
	}
	public int getSize_max() {
		return size_max;
	}
	public void setSize_max(int size_max) {
		this.size_max = size_max;
	}
	public int getSize_min() {
		return size_min;
	}
	public void setSize_min(int size_min) {
		this.size_min = size_min;
	}
	public int getSize_sum() {
		return size_sum;
	}
	public void setSize_sum(int size_sum) {
		this.size_sum = size_sum;
	}
	public int getSize_avg() {
		return size_avg;
	}
	public void setSize_avg(int size_avg) {
		this.size_avg = size_avg;
	}

	//����
	@Override
	public String toString() {
		return start+", "+count+", "+time_avg+", "+time_min+", "+time_max+", "+size_avg+", "+size_min+", "+size_max;
	}

	//�ð��� ����
	@Override
	public int compareTo(LogFormat logformat) {
		return this.start.compareTo(logformat.getStart());
	}
	
	
}
