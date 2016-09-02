package edu.wpi.ntrowles.cs4313.proj1;

import java.util.LinkedList;

public class GreedySearchQueue implements Queue {
	private LinkedList<Node> queue;
	
	public GreedySearchQueue(){
		
	}
	
	public void enqueue(Node node, Problem problem) {
		int numNodes = queue.size();
		for (int i=0; i<numNodes; i++){
			Node curNode = queue.get(i);
			if(Math.abs(node.getState() - problem.getEndNum()) < Math.abs(curNode.getState() - problem.getEndNum())){
				queue.add(i,node);
				return;
			}
		}
		queue.add(node);
	}

	public Node pop() {
		return queue.removeFirst();
	}
	
	public boolean isEmpty(){
		return queue.size() > 0;
	}

}
