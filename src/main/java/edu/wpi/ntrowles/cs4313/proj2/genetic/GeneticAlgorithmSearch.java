package edu.wpi.ntrowles.cs4313.proj2.genetic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
		final double timeBuffer = 0.05;
		
		//TODO create new genetic algorithm info object
		ArrayList<Solution> population = new ArrayList<Solution>();
		population.addAll(initPop);
		
		while(System.currentTimeMillis()/1000.0 < (initTimeMillis/1000.0 + prob.getMaxTime() - timeBuffer)){
			//check if any solution is correct
			for(Solution solution : population){
				if(fit.evaluateFitness(solution, prob) == 0){
					return generateGeneticSolutionInfo(solution);
				}
			}
			
			//create new population
			ArrayList<Solution> newPop = new ArrayList<Solution>();
			
			//create map
			Map<ArrayList<Solution>, Double> probMap = generateProbabilities(prob, fit, population);
			
			for(int i=0; i<population.size(); i++){
				//randomly select two children
				Solution x = randomSelection(probMap, population);
				Solution y = randomSelection(probMap, population);
				
				//breed child
				Solution child = reproduce(x,y);
				
				//mutate child
				child = mutate(child);
				
				//add child to new population
				newPop.add(child);
			}
			
			//assign new population to population
			population = newPop;
		}
		
		Solution bestSol = getBestSolution(population);
		
		
		//FIXME return genetic algorithm info object
		return null;
	}
	
	private Solution getBestSolution(ArrayList<Solution> population) {
		// TODO Auto-generated method stub
		return null;
	}


	private Map<ArrayList<Solution>, Double> generateProbabilities(Problem prob, Fitness fit,
			ArrayList<Solution> population) {
		// TODO Auto-generated method stub
		return null;
	}


	private GeneticSolutionInfo generateGeneticSolutionInfo(Solution solution) {
		// TODO Auto-generated method stub
		return null;
	}


	public Solution randomSelection(Map<ArrayList<Solution>, Double> probMap, ArrayList<Solution> population){
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
