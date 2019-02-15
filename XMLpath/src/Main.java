
public class Main {

	public static void main(String[] args)  {
		long start = System.currentTimeMillis();
		
		Xpath xml = new Xpath("resource/T_BASEFILE_TB.xml");
		xml.updateComment(xml.readId());
		
		long end = System.currentTimeMillis();
		System.out.println("Ω√∞£ : " + (end - start)/1000.0 + "√ ");
	}

}
