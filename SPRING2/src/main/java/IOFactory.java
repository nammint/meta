
public class IOFactory {
	private Input input;
	private Count count;
	private Output output;
	
	public Input getInput() {
		return input;
	}
	public void setInput(Input input) {
		this.input = input;
	}
	public Count getCount() {
		return count;
	}
	public void setCount(Count count) {
		this.count = count;
	}
	public Output getOutput() {
		return output;
	}
	public void setOutput(Output output) {
		this.output = output;
	}
	public void run() {
		String letters = input.read();
		int cntLetters = count.count(letters);
		output.write(cntLetters);
	}
}





