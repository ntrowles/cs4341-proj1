package edu.wpi.ntrowles.cs4313.proj1.utils;

import edu.wpi.ntrowles.cs4313.proj1.beans.Node;
import edu.wpi.ntrowles.cs4313.proj1.beans.Problem;

public interface Queue {
	public void enqueue(Node node, Problem problem);
	public Node pop();
}
