package edu.wpi.ntrowles.cs4313.proj1.greedy;

import java.util.LinkedList;

import edu.wpi.ntrowles.cs4313.proj1.beans.Node;
import edu.wpi.ntrowles.cs4313.proj1.beans.Problem;
import edu.wpi.ntrowles.cs4313.proj1.utils.Queue;

/**
 * 
 * @author ntrowles
 * 
 * []
 */
public class GreedySearchQueue implements Queue {
	private LinkedList<Node> queue;
	
	public GreedySearchQueue(){
		queue = new LinkedList<Node>();
	}
	
	public void enqueue(Node node, Problem problem) {
		int numNodes = queue.size();
		for (int i=0; i<numNodes; i++){
			Node curNode = queue.get(i);
			if(Math.abs(node.getState() - problem.getGoalNum()) < Math.abs(curNode.getState() - problem.getGoalNum())){
				queue.add(i,node);
				
				StringBuilder b = new StringBuilder();
				for(Node iNode : queue){
					b.append("" + iNode.getState() + ",");
				}
				System.out.println(b.toString());
				
				return;
			}
		}
		queue.add(node);
		
		StringBuilder b = new StringBuilder();
		for(Node curNode : queue){
			b.append("" + curNode.getState() + ",");
		}
		System.out.println(b.toString());
	}

	public Node pop() {
		return queue.removeFirst();
	}
	
	public boolean isEmpty(){
		return queue.size() == 0;
	}

}
