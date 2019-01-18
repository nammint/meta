
public class ConsoleWrite implements Output{
	@Override
	public void write(int cntLetters) {
		System.out.println("입력하신 단어의 수는 "+cntLetters);
	}

}
