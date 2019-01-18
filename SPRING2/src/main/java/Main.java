import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		GenericXmlApplicationContext  context = new GenericXmlApplicationContext("ApplicationContext.xml");
		context.close();
	}
}


