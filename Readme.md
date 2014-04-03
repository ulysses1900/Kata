//kata rules and some test cases

Word Wrapping Kata
1. There is given int max, which specifies the maximum row length (in characters).
3. You are given String input. If:
	string's length is shorter or equal to max, return the original input String
	string's length is longer than max, split input in the way, that last character in line is "-" or nothing (if you split on a white space) and white spaces at the end of the line are removed.
		
		all cases max = 4,
		"Oslo", split should be as follows: "Oslo"
		"London", split should be as follows: "Lon-\ndon"
		"L o n d o n", split should be as follows: "L o\nn d\no n"
		"Don Juan", split should be as follows: "Don\nJuan"
		"Dr Jekyll", split should be as follows: "Dr\nJek-\nyll"

4. You may assume, that white spaces occur only as single white spaces (there are no two white spaces one after another). You may assume, that there are no "-" characters in the input String.

//write down first test case
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;;

public class WordWrappingTest {
	@Test
	public void stringLengthLowerOrEqualToMaxReturnsInputString() {
		Wrapper wrapper = new Wrapper();
		wrapper.setMax(4);
		wrapper.setMin(2);
		String input = "Kot";
		String result = "";
		result = wrapper.split(input);

		assertEquals(result, "Kot");
	}
}
// TestRun: Failed (compilation error)

// write some code 
package wrap;

public class Wrapper {
	private int max;
	private int min;

	public String split(String input){		
		return input;
	}
		
	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}
}
// Total tests run: 1, Failures: 0, Skips: 0


//refactor
// imports are not displayed unless modified

public class WordWrappingTest {
	Wrapper wrapper;
	String input;
	String result;

	@BeforeMethod
	public void createWrapper() {
		wrapper = new Wrapper();
		wrapper.setMax(4);
		wrapper.setMin(2);
		input = "";
		result = "";
	}

	@Test
	public void stringLengthLowerOrEqualToMaxReturnsInputString() {
		input = "Kot";
		result = wrapper.split(s);
		assertEquals(result, "Kot");
	}
}

//add next test case

@Test
public void simpleStringLengthMoreThanMaxReturnsSplitString(){
	input = "Kotka";
	result = wrapper.split(input);
	assertEquals(result, "Kot-\nka");
}
	
//Total tests run: 2, Failures: 1, Skips: 0

//write some code
	
public String split(String input) {

	if (input.length() > max) {
		String temp = (input.substring(0, max - 1) + "-\n");
		input = temp + input.substring(max - 1);
		System.out.println("input: " + input);
	}
	return input;
}

//refactor (not necessary yet)

//add next test case

@Test
public void stringLengthMoreThanTwiceMaxReturnsSplitString(){
	input = "Smerfetka";
	result = wrapper.split(input);
	assertEquals(result, "Sme-\nrfe-\ntka");
}

//Total tests run: 3, Failures: 1, Skips: 0

//write some code

public String split(String input) {
		if (input.length() <= max) return input;
		String temp = "";
		while (input.length() > max) {
			temp += (input.substring(0, max - 1) + "-\n");
			input = input.substring(max - 1);
			if (input.length() > 0 && input.length() <= max){
				temp += input;
			}
		}
		return temp;
	}

//Total tests run: 3, Failures: 0, Skips: 0

//add some longer String to test

@Test
	public void stringLengthMoreThanFiveTimesMaxReturnsSplitString(){
		input = "FooBaaFooBaaFooBaa";
		result = wrapper.split(input);
		assertEquals(result, "Foo-\nBaa-\nFoo-\nBaa-\nFoo-\nBaa");
	}

//Total tests run: 4, Failures: 0, Skips: 0

//refactor

public String split(String input) {
		if (isLengthMoreThanZeroAndLessThanMax(input)) return input;
		String temp = "";
		while (input.length() > max) {
			temp += (input.substring(0, max - 1) + "-\n");
			input = input.substring(max - 1);
			if (isLengthMoreThanZeroAndLessThanMax(input)){
				temp += input;
			}
		}
		return temp;
	}
	
	public boolean isLengthMoreThanZeroAndLessThanMax(String s){
		if (s.length() > 0 && s.length()< max) return true;
		return false;
	}

//add another test case

@Test
public void stringNotMeetingMinValueOfSplitWord(){
	input = "Don Juan";
	result = wrapper.split(input);
	assertEquals(result, "Don\nJuan");
}

	//Total tests run: 5, Failures: 1, Skips: 0

// write some code
public String split(String input) {
	
	if (isLengthMoreThanZeroAndLessOrEqualToMax(input))
		return input;
	String temp = "";
	while (input.length() > max) {
		if (Character.isSpaceChar(input.charAt(max - 1))) {
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

//Total tests run: 5, Failures: 0, Skips: 0

//add some test case

@Test
public void twoStringsDividedBySpaceCharAtTheBeginningOfSecondString(){
	input = "Mata Hari";
	result = wrapper.split(input);
	assertEquals(result, "Mata\nHari");
}

//write some code
public String split(String input) {

	if (isLengthMoreThanZeroAndLessOrEqualToMax(input))
		return input;
	String temp = "";
	while (input.length() > max) {
		if (Character.isSpaceChar(input.charAt(max))) {
			temp += (input.substring(0, max) + "\n");
			input = input.substring(max+1);
		} else if (Character.isSpaceChar(input.charAt(max - 1))) {
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

// Total tests run: 6, Failures: 0, Skips: 0

//refactor
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

public boolean isSpace(String s, int i) {
	if (Character.isSpaceChar(s.charAt(i)))
		return true;
	return false;
}

//add test case

@Test
public void twoWordsDividedBySpaceCharSecondWord(){
	input = "Dr Jekyll";
	result = wrapper.split(input);
	assertEquals(result, "Dr\nJek-\nyll");
}

// Total tests run: 7, Failures: 1, Skips: 0

//write code

public String split(String input) {

	if (isLengthMoreThanZeroAndLessOrEqualToMax(input))
		return input;
	String temp = "";
	while (input.length() > max) {
		if (isSpace(input, max)) {
			temp += (input.substring(0, max) + "\n");
			input = input.substring(max + 1);
		} else if (isSpace(input, max - 1)) {
			temp += (input.substring(0, max - 1) + "\n");
			input = input.substring(max);
		} else if (isSpace(input, max - 2)) {
			temp += (input.substring(0, max - 2) + "\n");
			input = input.substring(max-1);
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

// Total tests run: 7, Failures: 0, Skips: 0

