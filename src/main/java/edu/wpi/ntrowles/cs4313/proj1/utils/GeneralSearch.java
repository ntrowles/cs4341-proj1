package edu.wpi.ntrowles.cs4313.proj1.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import edu.wpi.ntrowles.cs4313.proj1.beans.Node;
import edu.wpi.ntrowles.cs4313.proj1.beans.Problem;
import edu.wpi.ntrowles.cs4313.proj1.beans.Solution;
import edu.wpi.ntrowles.cs4313.proj1.beans.SolutionInfo;

public abstract class GeneralSearch implements Search {
	
	public SolutionInfo search(Problem problem, Queue nodeQueue) {
		//Start timer
		final Calendar startTime = Calendar.getInstance();
		final double maxTime = problem.getMaxTime();
		final double startTimeSec = startTime.getTimeInMillis()/1000;
		
		//more items from problem
		final double startNum = problem.getStartNum();
		final double goalNum = problem.getGoalNum();
		//--------------
		
		//Create variables to keep track of time, nodes expanded, and max search depth
		double timeToExec = 0;
		int nodesExpanded = 0;
		
		//Create solution
		Solution bestSolution = new Solution(new ArrayList<String>(), Double.MAX_VALUE);
		
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
				return generateSolutionInfo(bestSolution, problem);
			} 
			//otherwise, check if the current state is better than the current best solution
			//and assign it to bestSolution if it is
			else if(Math.abs(curSolution.getEndNum()-goalNum) < Math.abs(bestSolution.getEndNum()-goalNum)){
				bestSolution = curSolution;
			}
			
			//expand and enqueue
			List<Node> childNodes = expand(curNode, problem);
			for(Node node : childNodes){
				nodeQueue.enqueue(node, problem);
			}
			nodesExpanded++;
		}
		
		return generateSolutionInfo(bestSolution, problem);
	}
	
	public Solution generateSolution(Node node, Problem problem){
		//construct solution path
		List<String> path = new LinkedList<String>();
		Node curNode = node;
		while(curNode.getParent() != null){
			path.add(0, curNode.getOperator());
			curNode = curNode.getParent();
		}
		//construct calcNum, startNum, endNum
		double calcNum = node.getState();
		double startNum = problem.getStartNum();
		double endNum = problem.getEndNum();
		
		//construct solution
		Solution sol = new Solution(path, calcNum, startNum, endNum);
		return sol;
	}
	
	public abstract boolean goalTest(Node node, Problem problem);
	public abstract SolutionInfo generateSolutionInfo(Solution solution, Problem problem);
	public abstract List<Node> expand(Node node, Problem problem);
}
