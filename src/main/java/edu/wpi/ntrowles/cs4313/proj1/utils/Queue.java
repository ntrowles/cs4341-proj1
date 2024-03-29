package edu.wpi.ntrowles.cs4313.proj1.utils;

import edu.wpi.ntrowles.cs4313.proj1.beans.Node;
import edu.wpi.ntrowles.cs4313.proj1.beans.Problem;

/**
 * Generic queue interface.
 * 
 * @author ntrowles
 */
public interface Queue {
	
	public abstract void enqueue(Node node, Problem problem);
	public abstract Node pop();
	public abstract boolean isEmpty();
}
