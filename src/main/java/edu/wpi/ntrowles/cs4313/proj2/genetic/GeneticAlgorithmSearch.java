package edu.wpi.ntrowles.cs4313.proj2.genetic;

import java.util.ArrayList;

import edu.wpi.ntrowles.cs4313.proj1.beans.Problem;
import edu.wpi.ntrowles.cs4313.proj1.beans.Solution;
import edu.wpi.ntrowles.cs4313.proj1.beans.SolutionInfo;
import edu.wpi.ntrowles.cs4313.proj1.utils.Search;
import edu.wpi.ntrowles.cs4313.proj2.beans.GeneticSolutionInfo;
import edu.wpi.ntrowles.cs4313.proj2.utils.DifferenceFitness;
import edu.wpi.ntrowles.cs4313.proj2.utils.Fitness;

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
	
	public Solution mutate(Solution child){
		//TODO finish
		return null;
	}

}
