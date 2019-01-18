
public class Count {
	public int count(String letters) {
		String [] cnt = null;
		letters = letters.trim();
		letters = letters.replaceAll("\n", " ");
		letters = letters.replaceAll(" +", " ");
		cnt = letters.split(" ");
		return cnt.length;
	}
}
