package edu.wpi.ntrowles.cs4313.proj1;

import org.junit.Test;
import java.util.logging.*;

/**
 * GreedySearchTest is an object that tests the Greedy Search method.
 * 
 * @author ntrowles
 */
public class GreedySearchTest {
	
	//sub-optimal test
	@Test
	public void searchTest3(){
		String[] args = {"src/test/resources/greedy_0"};
		Main.main(args);
	}
	
	@Test
	public void searchTest1(){
		String[] args = {"src/test/resources/greedy_1"};
		Main.main(args);
	}
	
	//tests for termination at specified time limit
	@Test
	public void searchTest2(){
		String[] args = {"src/test/resources/greedy_2"};
		Main.main(args);
	}	
	
	@Test
	public void searchTest0(){
		String[] args = {"src/test/resources/greedy_3"};
		Main.main(args);
	}
	
	@Test
	public void searchTest4(){
		String[] args = {"src/test/resources/greedy_4"};
		Main.main(args);
	}
}
