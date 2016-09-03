package edu.wpi.ntrowles.cs4313.proj1.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.wpi.ntrowles.cs4313.proj1.beans.Node;
import edu.wpi.ntrowles.cs4313.proj1.beans.Problem;
import edu.wpi.ntrowles.cs4313.proj1.beans.Solution;
import edu.wpi.ntrowles.cs4313.proj1.beans.SolutionInfo;
import edu.wpi.ntrowles.cs4313.proj1.greedy.GreedySearchQueue;

public abstract class GeneralSearch implements Search {
	protected Queue nodeQueue;
	protected Problem problem;
	
	public GeneralSearch(Queue nodeQueue, Problem problem){
		this.nodeQueue = nodeQueue;
		this.problem = problem;
	}
	
	public SolutionInfo search(Problem problem) {
		//Start timer
		final Calendar startTime = Calendar.getInstance();
		final double maxTime = problem.getMaxTime();
		final double startTimeSec = startTime.getTimeInMillis()/1000;
		
		//more items from problem
		final double startNum = problem.getStartNum();
		final double goalNum = problem.getGoalNum();
		//--------------
		//Create Queue
		
		//Create variables to keep track of time, nodes expanded, and max search depth
		double timeToExec = 0;
		int nodesExpanded = 0;
		
		//Create solution
		final Solution bestSolution = new Solution(new ArrayList<String>(), Double.MAX_VALUE);
		
		//Add root node to queue
		double startState = problem.getStartNum();
		Node root = new Node(startState, null, null, 0, 0);
		nodeQueue.enqueue(root, problem);
		
		//Start search
		
		//loop through queue until it is empty, or time runs out
		while(!nodeQueue.isEmpty()){
			//Check if time is up, if it is, exit the loop (and then return the best solution)
			Calendar curTime = Calendar.getInstance();
			double curTimeSec = curTime.getTimeInMillis()/1000;
			if(curTimeSec > startTimeSec + maxTime){
				return generateSolutionInfo(bestSolution, problem);
			}
			
			//process first node in queue
			Node curNode = nodeQueue.pop();
			
			//Create solution from current node
			Solution curSolution = generateSolution(curNode, problem);
			
			//if it is goal state, return solution + info
			if(goalTest(curNode, problem)){
				
			} 
			//otherwise, check if the current state is better than the current best solution
			else if(Math.abs(curSolution.getEndNum()-goalNum) < Math.abs(bestSolution.getEndNum()-goalNum)){
				
			}
			
			//expand and enqueue
			List<Node> childNodes = expand(curNode, problem);
			for(Node node : childNodes){
				nodeQueue.enqueue(node, problem);
			}
		}
		
		return generateSolutionInfo(bestSolution, problem);
	}
	
	public abstract boolean goalTest(Node node, Problem problem);
	public abstract Solution generateSolution(Node node, Problem problem);
	public abstract SolutionInfo generateSolutionInfo(Solution solution, Problem problem);
	public abstract List<Node> expand(Node node, Problem problem);
}
