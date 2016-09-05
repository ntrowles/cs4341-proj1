package edu.wpi.ntrowles.cs4313.proj1;

import org.junit.Test;
import java.util.logging.*;

/**
 * 
 * @author ntrowles
 *
 * []
 */
public class GreedySearchTest {
	
	//sub-optimal test
	@Test
	public void searchTest1(){
		String[] args = {"src/test/resources/test_1"};
		Main.main(args);
	}
	
	//tests for termination at specified time limit
	@Test
	public void searchTest2(){
		String[] args = {"src/test/resources/test_2"};
		Main.main(args);
	}
	
	//optimal test
	@Test
	public void searchTest3(){
		String[] args = {"src/test/resources/test_3"};
		Main.main(args);
	}
}
