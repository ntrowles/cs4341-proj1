package edu.wpi.ntrowles.cs4313.proj2;

import org.junit.Test;

public class ExperimentsTest {
	@Test
	public void experimentTest(){
		double[][] timedResults = new double[10][14];
		String[] args = {"src/test/resources/genetic_0"};
		Main.main(args);
	}
}
