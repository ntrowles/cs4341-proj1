package edu.wpi.ntrowles.cs4313.proj1.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import edu.wpi.ntrowles.cs4313.proj1.beans.Node;
import edu.wpi.ntrowles.cs4313.proj1.beans.Problem;
import edu.wpi.ntrowles.cs4313.proj1.beans.Solution;
import edu.wpi.ntrowles.cs4313.proj1.beans.SolutionInfo;

public class GeneralSearch {
	
	public SolutionInfo search(Problem problem, Queue nodeQueue) {
		//Start timer
		final Calendar startTime = Calendar.getInstance();
		final double maxTime = problem.getMaxTime();
		final double startTimeSec = startTime.getTimeInMillis()/1000.0;
		
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
	
	public boolean goalTest(Node node, Problem problem){
		return (node.getState() == problem.getGoalNum());
	}
	
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
				solution = leftOperand + rightOperand; 
				break;
				
			case '-':
				solution = leftOperand - rightOperand;
				break;
				
			case '*':
				solution = leftOperand * rightOperand;
				break;
				
			case '/':
				solution = leftOperand / rightOperand;
				break;
				
			case '^':
				solution = Math.pow(leftOperand, rightOperand);
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
