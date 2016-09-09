package edu.wpi.ntrowles.cs4313.proj1;

import org.junit.Test;

/**
 * IterativeDeepeningTest is an object that tests the Iterative 
 * Deepening Search method.
 * 
 * @author ntrowles
 */
public class IterativeDeepeningSearchTest {
	@Test
	public void searchTest1(){
		String[] args = {"src/test/resources/test_3"};
		Main.main(args);
	}
	
	@Test
	public void searchTest2(){
		String[] args = {"src/test/resources/test_4"};
		Main.main(args);
	}
}
