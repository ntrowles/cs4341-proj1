package edu.wpi.ntrowles.cs4313.proj2;

import org.junit.Test;

import edu.wpi.ntrowles.cs4313.proj2.Main;

public class GeneticAlgorithmSearchTest {
	@Test
	public void searchTest1(){
		String[] args = {"src/test/resources/genetic_1"};
		Main.main(args);
	}
}
