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
import edu.wpi.ntrowles.cs4313.proj1.utils.GeneralSearch;
import edu.wpi.ntrowles.cs4313.proj1.utils.Search;

public class GreedySearch extends GeneralSearch {

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
		
		/*
		//construct timeToExec
		//FIXME change to actually get time
		double timeToExec = 0;
		
		//construct nodesExpanded, maxSearchDepth
		//TODO calculate at runtime
		int nodesExpanded = 0;
		int maxSearchDepth = 0;
		
		//solution constructed, no error message
		String errorMessage = "0";
		*/
		
		//construct solution
		Solution sol = new Solution(path, calcNum, startNum, endNum);
		return sol;
	}

}
