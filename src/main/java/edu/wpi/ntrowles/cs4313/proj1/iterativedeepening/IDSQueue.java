package edu.wpi.ntrowles.cs4313.proj1.iterativedeepening;

import java.util.LinkedList;
import java.util.List;

import edu.wpi.ntrowles.cs4313.proj1.beans.Node;
import edu.wpi.ntrowles.cs4313.proj1.beans.Problem;
import edu.wpi.ntrowles.cs4313.proj1.utils.Queue;

/**
 * 
 * 
 * @author ntrowles
 */
public class IDSQueue implements Queue {
	private final List<Node> nodeQueue;
	private final int depthLimit;
	
	/**
	 * Initializes an IDS queue of depth zero.
	 */
	public IDSQueue(){
		depthLimit = 0;
		this.nodeQueue = new LinkedList<Node>();
	}
	
	/**
	 * Initialize an IDS queue with a defined depth.
	 * 
	 * @param maxDepth	the depth the queue will be instantiated at.
	 */
	public IDSQueue(int maxDepth){
		depthLimit = maxDepth;
		this.nodeQueue = new LinkedList<Node>();
	}
	
	/**
	 * We always add to the front of the queue.
	 * This queue will act similar to (but exactly
	 * like) a stack.
	 * 
	 * @param node		the node to be added to the queue
	 * @param problem	information needed to record problem state
	 * 
	 */
	public void enqueue(Node node, Problem problem) {
		if(node.getDepth() <= depthLimit){
			nodeQueue.add(0, node);
		}
	}
	
    /**
     * We pop from the front as well, as we must
     * "travel" back up the tree.
     * 
     * @return the first node in queue
     */
	public Node pop() {
		return nodeQueue.remove(0);
	}

	/**
	 * Obligatory "empty" function to prevent bounds errors
	 * 
	 * @return 	true if queue is empty;
	 * 			false otherwise.
	 */
	public boolean isEmpty() {
		return nodeQueue.size() == 0;
	}
	
	
	

}
