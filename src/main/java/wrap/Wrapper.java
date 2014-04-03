package wrap;

public class Wrapper {
	private int max;

	public String split(String input) {

		if (isLengthMoreThanZeroAndLessOrEqualToMax(input))
			return input;
		String temp = "";
		while (input.length() > max) {
			if (isSpace(input, max)) {
				temp += (input.substring(0, max) + "\n");
				input = input.substring(max + 1);
			} else if (isSpace(input, max-1)) {
				temp += (input.substring(0, max - 1) + "\n");
				input = input.substring(max);
			} else {
				temp += (input.substring(0, max - 1) + "-\n");
				input = input.substring(max - 1);
			}
			if (isLengthMoreThanZeroAndLessOrEqualToMax(input)) {
				temp += input;
			}
		}
		return temp;
	}

	public boolean isLengthMoreThanZeroAndLessOrEqualToMax(String s) {
		if (s.length() > 0 && s.length() <= max)
			return true;
		return false;
	}

	public boolean isSpace(String s, int i) {
		if (Character.isSpaceChar(s.charAt(i)))
			return true;
		return false;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}
}
