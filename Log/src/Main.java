
public class Main {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		LogReader li = new LogReader("resource/galileo.log");
		String log1 = li.readLog1();
		String log2 = li.readLog2();
		li.writeFile("resource/Log1.txt",log1);
		li.writeFile("resource/Log2.txt",log2);
		
		long end = System.currentTimeMillis();
		System.out.println("시간 : " + (end - start)/1000.0 + "초");
		
		long memory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		System.out.println("메모리 사용량 : "+ memory/1000);
	}

}
