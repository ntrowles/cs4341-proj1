package edu.wpi.ntrowles.cs4313.proj1.utils;

import java.util.List;

import edu.wpi.ntrowles.cs4313.proj1.beans.Node;
import edu.wpi.ntrowles.cs4313.proj1.beans.Problem;

public interface Queue {
	
	public abstract void enqueue(Node node, Problem problem);
	public abstract Node pop();
	public abstract boolean isEmpty();
}
