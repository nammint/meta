
public class Main {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		LogReader li = new LogReader("resource/galileo.log");
		String log1 = li.readLog1();
		String log2 = li.readLog2();
		li.writeFile("resource/Log1.txt",log1);
		li.writeFile("resource/Log2.txt",log2);
		
		long end = System.currentTimeMillis();
		System.out.println("Ω√∞£ : " + (end - start)/1000.0 + "√ ");
	}

}
