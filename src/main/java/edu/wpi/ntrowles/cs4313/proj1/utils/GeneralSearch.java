package edu.wpi.ntrowles.cs4313.proj1.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import edu.wpi.ntrowles.cs4313.proj1.beans.Node;
import edu.wpi.ntrowles.cs4313.proj1.beans.Problem;
import edu.wpi.ntrowles.cs4313.proj1.beans.Solution;
import edu.wpi.ntrowles.cs4313.proj1.beans.SolutionInfo;

/**
 * GeneralSearch is the object the user will use to search for the solution to 
 * a specified problem.
 * <p>
 * The type of search that is performed is dependent on what type of queue is 
 * used in the search.
 * 
 * @see #search(Problem, Queue)
 * @author ntrowles
 */
public class GeneralSearch {
	
	/**
	 * Returns a solution SolutionInfo object containing all of the 
	 * information pertaining to the solution of a specified problem.
	 * 
	 * @param problem	information stating requirements/limitations of the
	 * 					search
	 * @param nodeQueue	a priority queue
	 * @return	SolutionInfo object containing the information pertaining to
	 * 			the solution of the problem
	 */
	public SolutionInfo search(Problem problem, Queue nodeQueue) {
		//Start timer
		final Calendar startTime = Calendar.getInstance();

		//Time buffer of 0.05 sec
		final double maxTime = problem.getMaxTime() - 0.05;
		final double startTimeSec = startTime.getTimeInMillis()/1000;
		
		//more items from problem
		final double startNum = problem.getStartNum();
		final double goalNum = problem.getGoalNum();
		
		//Create variables to keep track of time, nodes expanded, and max search depth
		int nodesExpanded = 0;
		int maxSearchDepth = 0;
		
		
		//Create solution
		Solution bestSolution = new Solution(new ArrayList<String>(), Double.MAX_VALUE);
		
		//Add root node to queue
		double startState = problem.getStartNum();
		Node root = new Node(startState, null, null, 0, 0);
		nodeQueue.enqueue(root, problem);
		
		//----------Start search--------------
		
		//loop through queue until it is empty, or time runs out
		while(!nodeQueue.isEmpty()){
			//Check if time is up, if it is, return the best solution
			Calendar curTime = Calendar.getInstance();
			double curTimeSec = curTime.getTimeInMillis()/1000.0;
			if(curTimeSec > startTimeSec + maxTime){
				return new SolutionInfo(bestSolution, startNum, goalNum, curTimeSec-startTimeSec, nodesExpanded, maxSearchDepth, 1);
			}
			
			//process first node in queue
			Node curNode = nodeQueue.pop();
			if(curNode.getDepth() >= maxSearchDepth){
				maxSearchDepth = curNode.getDepth();
			}
			
			//Create solution from current node
			Solution curSolution = generateSolution(curNode, problem);
			
			//if it is goal state, return solution + info
			if(goalTest(curNode, problem)){
				return new SolutionInfo(curSolution, startNum, goalNum, curTimeSec-startTimeSec, nodesExpanded, maxSearchDepth, 0);
			} 
			//otherwise, check if the current state is better than the current best solution
			//and assign it to bestSolution if it is
			else if(Math.abs(curSolution.getEndNum()-goalNum) < Math.abs(bestSolution.getEndNum()-goalNum)){
				bestSolution = curSolution;
			}
			
			//expand and enqueue
			List<Node> childNodes = expand(curNode, problem.getOperators());
			for(Node node : childNodes){
				nodeQueue.enqueue(node, problem);
			}
			nodesExpanded++;
		}
		double curTimeSec = Calendar.getInstance().getTimeInMillis()/1000.0;
		return new SolutionInfo(bestSolution, startNum, goalNum, curTimeSec-startTimeSec, nodesExpanded, maxSearchDepth, 2);
		
	}
	
	/**
	 * Produces a possible solution to the current problem.
	 * 
	 * @param node		the current Node
	 * @param problem	information stating requirements/limitations of the
	 * 					search
	 * @return	a Solution object containing a list of equations that represent
	 * 			the best search path 
	 */
	public Solution generateSolution(Node node, Problem problem){
		//construct solution path
		List<String> path = new LinkedList<String>();
		Node curNode = node;
		while(curNode.getParent() != null){
			path.add(0, curNode.getOperator());
			curNode = curNode.getParent();
		}
		//construct goalNum
		double goalNum = node.getState();
		
		//construct solution
		Solution sol = new Solution(path, goalNum);
		return sol;
	}
	
	/**
	 * Tests to see if the current Node is contains the goal state.
	 * 
	 * @param node		the current Node
	 * @param problem	information stating requirements/limitations of the
	 * 					search
	 * @return	true if the numerical state of node is equal to the 
	 * 			goal state; false otherwise.
	 */
	public boolean goalTest(Node node, Problem problem){
		return (node.getState() == problem.getGoalNum());
	}
	
	/**
	 * Calculates the numerical state of Nodes expanded from current
	 * node and places them in a list of Nodes.
	 * 
	 * @param node		the current node
	 * @param operators	list of operators that can be executed on the
	 * 					current node
	 * @return	list of Nodes, each Node contaiing a numerical value
	 * 			equal to the result of an operation being executed
	 * 			on the current node
	 */
	public List<Node> expand(Node node, List<String> operators){
		List<Node> nodes = new ArrayList<Node>();
		
		for(String oper : operators){
			double leftOperand = node.getState();
			char operator = oper.charAt(0);
			String rightOperandString = oper.substring(2);
			double rightOperand = Double.parseDouble(rightOperandString);
			double solution = 0;
			
			switch(operator){
			case '+':
				solution = (int)(leftOperand + rightOperand); 
				break;
				
			case '-':
				solution = (int)(leftOperand - rightOperand);
				break;
				
			case '*':
				solution = (int)(leftOperand * rightOperand);
				break;
				
			case '/':
				solution = (int)(leftOperand / rightOperand);
				break;
				
			case '^':
				solution = (int)(Math.pow(leftOperand, rightOperand));
				break;
				
			default:
				System.out.println("Unable to process operator type: " + operator + "; Please use +,-,*,/,^");
				break;
			}
			
			Node genNode = new Node(solution, node, oper, node.getDepth()+1, node.getPathCost()+1);
			nodes.add(genNode);
		}
		return nodes;
	}
}
