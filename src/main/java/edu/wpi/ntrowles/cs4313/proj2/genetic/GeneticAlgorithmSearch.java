package edu.wpi.ntrowles.cs4313.proj2.genetic;

import java.util.ArrayList;

import edu.wpi.ntrowles.cs4313.proj1.beans.Problem;
import edu.wpi.ntrowles.cs4313.proj1.beans.Solution;
import edu.wpi.ntrowles.cs4313.proj1.beans.SolutionInfo;
import edu.wpi.ntrowles.cs4313.proj1.utils.Search;
import edu.wpi.ntrowles.cs4313.proj2.beans.GeneticSolutionInfo;
import edu.wpi.ntrowles.cs4313.proj2.utils.DifferenceFitness;
import edu.wpi.ntrowles.cs4313.proj2.utils.Fitness;

import java.util.Random;

public class GeneticAlgorithmSearch implements Search {

	public SolutionInfo search(Problem problem) {
		//create initial population
		ArrayList<Solution> population = generateInitialPopulation(problem);
		
		
		//call genetic algorithm
		Fitness fit = new DifferenceFitness();
		return geneticAlgorithmSearch(problem, fit, population, System.currentTimeMillis());
	}
	
	
	public ArrayList<Solution> generateInitialPopulation(Problem prob){
		ArrayList<Solution> population = new ArrayList<Solution>();
		
		//generate initial population
		
		return population;
	}
	
	public GeneticSolutionInfo geneticAlgorithmSearch(Problem prob, Fitness fit, ArrayList<Solution> initPop, long initTimeMillis){
		
		//TODO create new genetic algorithm info object
		ArrayList<Solution> population = new ArrayList<Solution>();
		population.addAll(initPop);
		
		while(System.currentTimeMillis()/1000.0 < (initTimeMillis/1000.0 + prob.getMaxTime())){
			//check if any solution is correct
			
			
			ArrayList<Solution> newPop = new ArrayList<Solution>();
			Solution x = randomSelection(prob, fit, population);
			Solution y = randomSelection(prob, fit, population);
			
			//Solution child
			
		}
		
		
		
		//FIXME return genetic algorithm info object
		return null;
	}
	
	public Solution randomSelection(Problem prob, Fitness fit, ArrayList<Solution> population){
		//TODO finish
		return null;
	}
	
	public Solution reproduce(Solution x, Solution y){
		//TODO finish
		return null;
	}
	
	/**
	 * 
	 * 
	 * @param child
	 * @return
	 */
	public Solution mutate(Problem problem, Solution child){
		//Set random object (java.util.random) to determine random probability requirement
		Random rand = new Random();
		int req = rand.nextInt(99);
		
		//Select some random number
		int x = rand.nextInt(99);
		//if (number selected adheres to probability requirement): mutate
		if(x < req){
			
			//choose randomly what path to mutate
			int pathNum = rand.nextInt(child.getPath().size() - 1);
			
			//choose randomly what operator to select
			int opNum = rand.nextInt(problem.getOperators().size() - 1);
			
			//generate new values
			
			
			//return mutated child
		}
		//return original child since mutation did not occur
		return child;
	}

}
