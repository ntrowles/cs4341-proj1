package edu.wpi.ntrowles.cs4313.proj1.iterativedeepening;

import java.util.LinkedList;
import java.util.List;

import edu.wpi.ntrowles.cs4313.proj1.beans.Node;
import edu.wpi.ntrowles.cs4313.proj1.beans.Problem;
import edu.wpi.ntrowles.cs4313.proj1.utils.Queue;

public class IDSQueue implements Queue {
	private final List<Node> nodeQueue;
	
	public IDSQueue(){
		this.nodeQueue = new LinkedList<Node>();
	}
	
	public void enqueue(Node node, Problem problem) {
		// TODO Auto-generated method stub

	}

	public Node pop() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

}
