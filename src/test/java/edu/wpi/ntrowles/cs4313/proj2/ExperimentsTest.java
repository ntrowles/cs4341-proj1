package edu.wpi.ntrowles.cs4313.proj2;

import java.io.IOException;

import org.junit.Test;

import edu.wpi.ntrowles.cs4313.proj1.beans.Problem;
import edu.wpi.ntrowles.cs4313.proj1.utils.ProblemParser;
import edu.wpi.ntrowles.cs4313.proj2.genetic.GeneticAlgorithmSearch;

public class ExperimentsTest {
	@Test
	public void experimentTest(){
		ProblemParser pars = new ProblemParser();
		Problem problem;
		try {
			problem = pars.parse("src/test/resources/genetic_0");
		} catch (IOException e) {
			System.out.println("Unable to load information from file");
			e.printStackTrace();
			return;
		}
		
		double[] times = {0.25, 0.5, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 60};
		double[][] timedResults = new double[10][14];
		GeneticAlgorithmSearch search = new GeneticAlgorithmSearch(20);
		
		//run this 10 times
		for(int i=0; i<10; i++){
			//double[i] = search.search(problem);
		}

	}
}
