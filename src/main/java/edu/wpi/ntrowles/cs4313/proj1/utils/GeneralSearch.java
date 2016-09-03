package edu.wpi.ntrowles.cs4313.proj1.utils;

import java.util.List;

import edu.wpi.ntrowles.cs4313.proj1.beans.Node;
import edu.wpi.ntrowles.cs4313.proj1.beans.Problem;
import edu.wpi.ntrowles.cs4313.proj1.beans.Solution;
import edu.wpi.ntrowles.cs4313.proj1.greedy.GreedySearchQueue;

public abstract class GeneralSearch implements Search {
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
	
	public abstract boolean goalTest(Node node, Problem problem);
	public abstract Solution generateSolution(Node node, Problem problem);
}
