package edu.wpi.ntrowles.cs4313.proj1.greedy;

/**
 * 
 * @author Nick Rowles
 * @author Tommy Trieu
 * Class containing the Greedy Search informed search method.
 *
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import edu.wpi.ntrowles.cs4313.proj1.beans.Node;
import edu.wpi.ntrowles.cs4313.proj1.beans.Problem;
import edu.wpi.ntrowles.cs4313.proj1.beans.Solution;
import edu.wpi.ntrowles.cs4313.proj1.utils.Search;

public class GreedySearch implements Search {

	
	public Solution search(Problem moProblems) {
		//Create Queue
		GreedySearchQueue nodeQueue = new GreedySearchQueue();
		
		//Create solution
		Solution bestSolution;
		
		//Add root node to queue
		double startState = moProblems.getStartNum();
		Node root = new Node(startState, null, null, 0, 0);
		nodeQueue.enqueue(root, moProblems);
		
		//Start search
		
		//loop through queue until it is empty
		while(!nodeQueue.isEmpty()){
			//process first node
			Node curNode = nodeQueue.pop();
			
			//if it is goal state, return solution
			if(goalTest(curNode, moProblems)){
				return generateSolution(curNode, moProblems);
			}
			
			//expand and enqueue
			List<Node> childNodes = expand(curNode, moProblems.getOperators());
			for(Node node : childNodes){
				nodeQueue.enqueue(node, moProblems);
			}
		}
		return null;
		//Solution solution = (path, calcNum, )
	}
	
	public boolean goalTest(Node node, Problem problem){
		return (node.getState() == problem.getEndNum());
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
		
		
		//construct timeToExec
		//FIXME change to actually get time
		double timeToExec = 0;
		
		//construct nodesExpanded, maxSearchDepth
		//TODO calculate at runtime
		int nodesExpanded = 0;
		int maxSearchDepth = 0;
		
		//solution constructed, no error message
		String errorMessage = "0";
		
		//construct solution
		Solution sol = new Solution(path, calcNum, startNum, endNum, timeToExec, nodesExpanded, maxSearchDepth, errorMessage);
		return sol;
	}

}
