import java.util.Scanner;

public class ConsoleRead implements Input{
	Scanner sc = null;
	
	@Override
	public String read() {
		sc = new Scanner(System.in);
		System.out.println("문자를 입력해주세요.");
		String letters = sc.nextLine();
		sc.close();
		return letters;
	}

}
