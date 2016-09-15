package edu.wpi.ntrowles.cs4313.proj2.genetic;

import java.util.ArrayList;

import java.util.Random;
import edu.wpi.ntrowles.cs4313.proj1.beans.Problem;
import edu.wpi.ntrowles.cs4313.proj1.beans.Solution;
import edu.wpi.ntrowles.cs4313.proj1.beans.SolutionInfo;
import edu.wpi.ntrowles.cs4313.proj1.utils.Search;
import edu.wpi.ntrowles.cs4313.proj2.beans.GeneticSolutionInfo;
import edu.wpi.ntrowles.cs4313.proj2.utils.DifferenceFitness;
import edu.wpi.ntrowles.cs4313.proj2.utils.Fitness;

public class GeneticAlgorithmSearch implements Search {

	//Random object 
	Random rand = new Random();
	
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
	
	/**
	 * Generate a new solution based on cutoff point of
	 * x and y solution paths as well as their endNums.
	 * @param x Parent 1
	 * @param y Parent 2
	 * @return the new child solution
	 */
	public Solution reproduce(Solution x, Solution y){
		//Path of the solution so far
		int n = x.getPath().size();
		
		//Cutoff point randomly from 1 to n
		int c = rand.nextInt(n);
		
		//Create the new path by taking from both X and y
		ArrayList<String> aPathy = new ArrayList<String>();
		for(int i = 0; i < n; i++){
			if(i < c){
				aPathy.add(x.getPath().get(i));
			}
			else
				aPathy.add(y.getPath().get(i));
		}
		
		//New endNum is right now the average between x and y
		double newEnd = x.calcEndNum(x.getStartNum(), aPathy);
		
		Solution child = new Solution(aPathy, newEnd, x.getStartNum());
		return child;
	}
	
	public Solution mutate(Solution child){
		//TODO finish
		return null;
	}

}
