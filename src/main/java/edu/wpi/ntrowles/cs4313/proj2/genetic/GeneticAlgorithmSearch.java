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

public class GeneticAlgorithmSearch implements Search {
	//private data
	private int popSize;

	//Random object 
	Random rand = new Random();
	

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
	
	//functions

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
		for(int i = 0; i<popSize; i++){
			//randomly generate path
			//randomly generate size of path
			int randPathSize = (int)(Math.random() * 10); //random path size [1,10]
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
	
	public GeneticSolutionInfo geneticAlgorithmSearch(Problem prob, Fitness fit, ArrayList<Solution> initPop, long initTimeMillis){
		final double timeBuffer = 0.05;
		
		ArrayList<Solution> population = new ArrayList<Solution>();
		population.addAll(initPop);
		
		while(System.currentTimeMillis()/1000.0 < (initTimeMillis/1000.0 + prob.getMaxTime() - timeBuffer)){
			//check if any solution is correct
			for(Solution solution : population){
				if(fit.evaluateFitness(solution, prob) == 0){
					return generateGeneticSolutionInfo(prob, solution);
				}
			}
			
			//create new population
			ArrayList<Solution> newPop = new ArrayList<Solution>();
			
			//create map
			//Map<Solution, Double> probMap = generateProbabilities(prob, fit, population);
			
			for(int i=0; i < 1; i++){
				//randomly select two children
				
				Solution x = randomSelection(prob, population);
				Solution y = randomSelection(prob, population);
				
				//breed child
				Solution child = reproduce(x,y);
				
				//mutate child
				child = mutate(prob, child);
				
				//add child to new population
				newPop.add(child);
			}
			
			//assign new population to population
			population = newPop;
		}
		
		Solution bestSol = getBestSolution(population, prob, fit);
		return generateGeneticSolutionInfo(prob, bestSol);
	}
	
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
		Map<Solution, Double> probMap = new HashMap();
		
		double goalNum = prob.getGoalNum();
		
		for(int i = 0; i < population.size(); i++){
			probMap.put(population.get(i), 1/(Math.abs(goalNum - population.get(i).getEndNum())));
		}
		
		return probMap;
	}
*/

	private GeneticSolutionInfo generateGeneticSolutionInfo(Problem prob, Solution solution) {
		GeneticSolutionInfo sol = new GeneticSolutionInfo(solution, prob.getGoalNum(), 9001, popSize, popSize, popSize);
		return sol;
	}


	public Solution randomSelection(Problem prob, ArrayList<Solution> population){
		double goalNum = prob.getGoalNum();
		Double totalSum = 0.0;
		
		for(int i = 0; i < population.size(); i++){
			totalSum += 1/(Math.abs(goalNum - population.get(i).getEndNum()));
		}

		Double selection = rand.nextDouble()*totalSum;
		Double sum = 0.0;
		int i = 0;
		
		while(sum < selection){
			sum += 1/(Math.abs(goalNum - population.get(i).getEndNum()));
			i++;
		}
		
		return population.get(i-1);
	}
	
	/**
	 * Generate a new solution based on cutoff point of
	 * x and y solution paths as well as their endNums.
	 * 
	 * @param x Parent 1
	 * @param y Parent 2
	 * @return the new child solution
	 */
	public Solution reproduce(Solution x, Solution y){
		//Path of the solution so far
		int n = x.getPath().size();
		
		//Cutoff point randomly from 1 to n
		int c = (int) (Math.random()*n);
		
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
	 * Replace, insert, delete
	 * 
	 * @param child
	 * @return
	 */
	public Solution mutate(Problem problem, Solution child){
		int type = (int) (Math.random()*4);
		
		//if (number selected adheres to probability requirement): mutate
		if(type == 1){ //replace
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
		else if(type == 2){ //insert
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
		else if(type == 3){ //delete
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
		else{
			//return original child since mutation did not occur
			return child;
		}
	}


}
