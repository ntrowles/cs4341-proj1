package edu.wpi.ntrowles.cs4313.proj2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import edu.wpi.ntrowles.cs4313.proj1.beans.Problem;
import edu.wpi.ntrowles.cs4313.proj1.beans.Solution;
import edu.wpi.ntrowles.cs4313.proj1.utils.ProblemParser;
import edu.wpi.ntrowles.cs4313.proj2.genetic.GeneticAlgorithmSearch;
import edu.wpi.ntrowles.cs4313.proj2.utils.DifferenceFitness;

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
		double[] summedResults = new double[14];
		GeneticAlgorithmSearch searcher = new GeneticAlgorithmSearch(20);
		
		//run this 10 times
		for(int i=0; i<10; i++){
			Map<Double, Solution> timedSol = searcher.geneticAlgorithmSearch(problem, new DifferenceFitness(), times);
			for(int j=0; j<times.length; j++){
				System.out.println(timedSol.get(times[j]).printString());
				summedResults[j] += Math.abs(timedSol.get(new Double(times[j])).getEndNum() - problem.getGoalNum());
			}
			System.out.println("suite ran");
		}
		
		Map<Double, Double> graph = new HashMap<Double, Double>();
		for(int i=0; i<times.length; i++){
			graph.put(times[i], summedResults[i]/10);
			System.out.println("Time: " + times[i] + " - Average Error: " + summedResults[i]/10);
		}

		
	}
}
