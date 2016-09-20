package edu.wpi.ntrowles.cs4313.proj2.genetic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import java.util.Random;

import edu.wpi.ntrowles.cs4313.proj1.beans.Problem;
import edu.wpi.ntrowles.cs4313.proj1.beans.Solution;
import edu.wpi.ntrowles.cs4313.proj1.beans.SolutionInfo;
import edu.wpi.ntrowles.cs4313.proj1.utils.Search;
import edu.wpi.ntrowles.cs4313.proj2.beans.GeneticSolutionInfo;
import edu.wpi.ntrowles.cs4313.proj2.utils.DifferenceFitness;
import edu.wpi.ntrowles.cs4313.proj2.utils.Fitness;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneticAlgorithmSearch implements Search {
	static final Logger logger = LoggerFactory.getLogger(GeneticAlgorithmSearch.class);
	
	//private data
	private int popSize;
	
	//getters and setters
	public int getPopSize() {
		return popSize;
	}
	public void setPopSize(int popSize) {
		this.popSize = popSize;
	}
	
	//constructor
	public GeneticAlgorithmSearch(int popSize){
		this.setPopSize(popSize);
	}
	
	/**
	 * Triggers the genetic search
	 * @param problem The problem state we are dealing with, used as basis to generate population.
	 * @return The resulting solution as well as pertinent info such as time,
	 * nodes expanded, and the kind of error encountered.
	 */
	public SolutionInfo search(Problem problem) {
		logger.debug("Genetic Search started");
		//create initial population
		ArrayList<Solution> population = generateInitialPopulation(problem);
				
		//call genetic algorithm
		Fitness fit = new DifferenceFitness();
		return geneticAlgorithmSearch(problem, fit, population, System.currentTimeMillis());
	}
	
	/**
	 * Randomly generates an initial population.
	 * @param prob Problem state
	 * @return An ArrayList of Solutions.
	 */
	public ArrayList<Solution> generateInitialPopulation(Problem prob){
		ArrayList<Solution> population = new ArrayList<Solution>();
		//generate initial population
		for(int i = 0; i<popSize; i++){
			//randomly generate path
			//randomly generate size of path
			int randPathSize = (int)(Math.random() * 10) + 1; //random path size [1, 10]
			List<String> path = new ArrayList<String>(randPathSize);
			//randomly generate each operator in path
			for(int j=0; j<randPathSize; j++){
				List<String> operators = prob.getOperators();
				int randOpIndex = (int)(Math.random()*operators.size());
				path.add(operators.get(randOpIndex));
			}
			
			Solution child = new Solution(prob.getStartNum(), path);
			population.add(child);
		}
		
		return population;
	}
	
	/**
	 * The search particular to the genetic algorithm.
	 * @param prob Initial problem state used to get max time.
	 * @param fit A fitness object which is used to check correctness of solutions.
	 * @param initPop An array list of the initial population.
	 * @param initTimeMillis An initial established time based off the system.
	 * @return All solution information pertinent to the genetic search.
	 */
	public GeneticSolutionInfo geneticAlgorithmSearch(Problem prob, Fitness fit, ArrayList<Solution> initPop, long initTimeMillis){
		final double timeBuffer = 0.05;
		
		//keep track of generation number
		int curGen = 0;
		
		ArrayList<Solution> population = new ArrayList<Solution>();
		population.addAll(initPop);
		
		while(System.currentTimeMillis()/1000.0 < (initTimeMillis/1000.0 + prob.getMaxTime() - timeBuffer)){
			
			//create new population
			ArrayList<Solution> newPop = new ArrayList<Solution>();
			
			//culling-elitism
			double cullThresh = 0.1;
			double eliteThresh = 0.5;
			for(int i = 0; i < population.size(); i++){
				if(Math.abs(population.get(i).getEndNum() - prob.getStartNum())/(prob.getGoalNum() - prob.getStartNum()) <= cullThresh){
					population.remove(i);
					i--;
				}
				else if(Math.abs(population.get(i).getEndNum() - prob.getStartNum())/(prob.getGoalNum() - prob.getStartNum()) >= eliteThresh){
					newPop.add(population.get(i));
				}
			}

			//log current generation
			for(int i=0; i<population.size(); i++){
				Solution curSol = population.get(i);
				logger.debug("Generation " + curGen + ", Organism " + i + curSol.printString());
			}

			//check if any solution is correct
			for(Solution solution : population){
				if(fit.evaluateFitness(solution, prob) == 0){
					return generateGeneticSolutionInfo(prob, solution, initTimeMillis, curGen, 0);
				}
			}
			
			
			
			//create map
			//Map<Solution, Double> probMap = generateProbabilities(prob, fit, population);
			
			for(int i=0; i < popSize; i++){
				//randomly select two children
				logger.debug("Selecting parents for reproduction");
				Solution x = randomSelection(prob, population);
				Solution y = randomSelection(prob, population);
				logger.debug("Parent 1: " + x.printString());
				logger.debug("Parent 2: " + y.printString());
				
				//breed child
				Solution child = reproduce(x,y);
				logger.debug("Child: " + child.printString());
				
				//mutate child
				child = mutate(prob, child);
				
				//add child to new population
				newPop.add(child);
			}
			
			//assign new population to population, update curGen
			population = newPop;
			curGen++;
		}
		Solution bestSol = getBestSolution(population, prob, fit);

		return generateGeneticSolutionInfo(prob, bestSol, initTimeMillis, curGen, 1);
	}
	
	/**
	 * Helper function used to obtain the best solution of a population.
	 * @param population Array list of solutions.
	 * @param prob Initial problem state used in the fitness function.
	 * @param fit Fitness object used to find the best fitting solution.
	 * @return Solution closest to the goal as evaluated by the fitness function.
	 */
	private Solution getBestSolution(ArrayList<Solution> population, Problem prob, Fitness fit){
		Solution bestSol = population.get(0);
		double bestSolFit = fit.evaluateFitness(population.get(0), prob);
		for(Solution sol : population){
			double curFit = fit.evaluateFitness(sol, prob);
			if(curFit < bestSolFit){
				bestSolFit = curFit;
				bestSol = sol;
			}
		}
		return bestSol;
	}

/*
	private Map<Solution, Double> generateProbabilities(Problem prob, Fitness fit, ArrayList<Solution> population) {
		Map<Solution, Double> probMap = new HashMap<Solution,Double>();
		
		double goalNum = prob.getGoalNum();
		
		for(int i = 0; i < population.size(); i++){
			probMap.put(population.get(i), 1/fit.evaluateFitness(population.get(i), prob));
		}
		
		return probMap;
	}
*/


	/**
	 * Helper function to generate the solution info particular for the genetic algorithm.
	 * @param prob Initial problem state.
	 * @param solution An organism.
	 * @param initTimeMillis An established initial time based off the system.
	 * @param numGen The actual number generated from the solution.
	 * @param errNum An enumerated error.
	 * @return A generated geneticSolutionInfo object.
	 */
	private GeneticSolutionInfo generateGeneticSolutionInfo(Problem prob, Solution solution, long initTimeMillis, int numGen, int errNum) {
		return new GeneticSolutionInfo(solution, prob.getGoalNum(), System.currentTimeMillis()/1000.0 - initTimeMillis/1000.0, popSize, numGen, errNum);
	}



	/**
	 * Stochastically selects a solution based on summed probabilities.
	 * @param prob Initial problem state.
	 * @param population ArrayList of solutions.
	 * @return One particular solution.
	 */
	public Solution randomSelection(Problem prob, ArrayList<Solution> population){
		double goalNum = prob.getGoalNum();
		Double totalSum = 0.0;
		
		for(int i = 0; i < population.size(); i++){
			totalSum += 1/(Math.abs(goalNum - population.get(i).getEndNum()));
		}

		Double selection = Math.random()*totalSum;
		Double sum = 0.0;
		int i = 0;
		
		do{
			sum += 1/(Math.abs(goalNum - population.get(i).getEndNum()));
			i++;
		}while(sum < selection);
		
		return population.get(i-1);
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
		

		//Cutoff point randomly from index 0 to index n - 1
		int c = (int)(Math.random()*n);

		
		//Create the new path by taking from both X and y
		ArrayList<String> aPathy = new ArrayList<String>();
		for(int i = 0; i < n; i++){
			if(i < c){
				aPathy.add(x.getPath().get(i));
			}
			else if(i < y.getPath().size()){
				aPathy.add(y.getPath().get(i));
			}
		}
		
		//New endNum is right now the average between x and y
		double newEnd = x.calcEndNum(x.getStartNum(), aPathy);
		
		Solution child = new Solution(aPathy, newEnd, x.getStartNum());
		return child;
	}
	
	/**
	 * Replace, insert, delete, as described in their repsective javadocs.
	 * @param problem Intitial problem state.
	 * @param child Solution to be mutated.
	 * @return mutated solution (child)
	 */
	public Solution mutate(Problem problem, Solution child){
		int type = (int) (Math.random()*4);
		
		//if (number selected adheres to probability requirement): mutate
		if(type == 1){ 
			Solution sol = replace(problem, child); //Replace the problem child.
			logger.debug("Child mutated, replacement occured. New child: \n" + sol.printString());
			return sol;
		}
		
		else if(type == 2){
			Solution sol = insert(problem, child); //Insert the problem child.
			logger.debug("Child mutated, insertion occured. New child: \n" + sol.printString());
			return sol;
		}
		
		else if(type == 3){ //delete
			Solution sol = delete(problem, child); //Delete the problem child.
			logger.debug("Child mutated, deletion occured. New child: \n" + sol.printString());
			return sol;
		}
		else{
			//return original child since mutation did not occur
			return child;
		}
	}
	
	/**
	 * Removes one particular node operator and switches it with another 
	 * randomly selected operator.
	 * @param problem Initial problem state, used for obtaining operators.
	 * @param child Solution to be mutated.
	 * @return A new solution with a replaced operator.
	 */
	private Solution replace (Problem problem, Solution child){
		//choose randomly what path to mutate
		int pathNum = (int) Math.random()*child.getPath().size();
		
		//choose randomly what operator to select
		int opNum = (int) Math.random()*problem.getOperators().size();
		
		//generate new values through replacement
		LinkedList<String> newPath = new LinkedList<String>();
		
		for(int i = 0; i < child.getPath().size(); i++){
			if(i == pathNum){
				newPath.add(i, problem.getOperators().get(opNum));
			}
			else{
				newPath.add(i, child.getPath().get(i));
			}
		}
		Solution newChild = new Solution(child.getStartNum(), newPath);
		
		//return mutated child
		return newChild;
	}
	
	/**
	 * Insert one new node inside the path of a randomly selected solution
	 * @param problem Initial problem state, used for obtaining operators.
	 * @param child Solution to be mutated.
	 * @return A new solution with a replaced operator.
	 */
	private Solution insert(Problem problem, Solution child){
		//choose randomly what path to insert after
		int pathNum = (int) Math.random()*child.getPath().size();
		
		//choose randomly what operator to select
		int opNum = (int) Math.random()*problem.getOperators().size();
		
		//generate new values
		LinkedList<String> newPath = new LinkedList<String>();
		
		for(int i = 0; i < child.getPath().size(); i++){
			if(i == pathNum){
				newPath.addLast(child.getPath().get(i));
				newPath.addLast(problem.getOperators().get(opNum));
			}
			else{
				newPath.addLast(child.getPath().get(i));
			}
		}
		Solution newChild = new Solution(child.getStartNum(), newPath);
		
		//return mutated child
		return newChild;
	}
	
	/**
	 * Removes one particular operator and switches it with another 
	 * randomly selected operator.
	 * @param problem Initial problem state, used for obtaining operators.
	 * @param child Solution to be mutated.
	 * @return A new solution with a replaced operator.
	 */
	private Solution delete (Problem problem, Solution child){
		//choose randomly what path to delete
		int pathNum = (int) Math.random()*child.getPath().size();
		
		//generate new values
		LinkedList<String> newPath = new LinkedList<String>();
		
		for(int i = 0; i < child.getPath().size(); i++){
			if(i != pathNum){
				newPath.addLast(child.getPath().get(i));
			}
		}
		Solution newChild = new Solution(child.getStartNum(), newPath);
		
		//return mutated child
		return newChild;
	}


}
