import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import wrap.Wrapper;
import static org.testng.Assert.assertEquals;

public class WordWrappingTest {
	Wrapper wrapper;
	String input;
	String result;

	@BeforeMethod
	public void createWrapper() {
		wrapper = new Wrapper();
		wrapper.setMax(4);
		input = "";
		result = "";
	}

	@Test
	public void stringLengthLowerOrEqualToMaxReturnsInputString() {
		input = "Kot";
		result = wrapper.split(input);
		assertEquals(result, "Kot");
	}
	
	@Test
	public void stringLengthMoreThanMaxReturnsSplitString(){
		input = "Kotka";
		result = wrapper.split(input);
		assertEquals(result, "Kot-\nka");
	}
	
	@Test
	public void stringLengthMoreThanTwiceMaxReturnsSplitString(){
		input = "Smerfetka";
		result = wrapper.split(input);
		assertEquals(result, "Sme-\nrfe-\ntka");
	}
	
	@Test
	public void stringLengthMoreThanFiveTimesMaxReturnsSplitString(){
		input = "FooBaaFooBaaFooBaa";
		result = wrapper.split(input);
		assertEquals(result, "Foo-\nBaa-\nFoo-\nBaa-\nFoo-\nBaa");
	}
	
	@Test
	public void twoWordsDividedBySpaceCharAtTheEndOfFirstWord(){
		input = "Don Juan";
		result = wrapper.split(input);
		assertEquals(result, "Don\nJuan");
	}
	
	@Test
	public void twoWordsDividedBySpaceCharAtTheBeginningOfSecondWord(){
		input = "Mata Hari";
		result = wrapper.split(input);
		assertEquals(result, "Mata\nHari");
	}
	
	@Test
	public void twoWordsDividedBySpaceCharSecondWord(){
		input = "Dr Jekyll";
		result = wrapper.split(input);
		assertEquals(result, "Dr\nJek-\nyll");
	}
}
