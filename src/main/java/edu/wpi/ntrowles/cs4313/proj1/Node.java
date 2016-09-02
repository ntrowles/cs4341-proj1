package edu.wpi.ntrowles.cs4313.proj1;

public class Node {
	private double state; //curent value
	private Node parent;  //parend node
	private char operator;
	private int depth;
	private double pathCost;
	
	public Node(double state, Node parent, char operator, int depth, double pathCost){
		this.state = state;
		this.parent = parent;
		this.operator = operator;
		this.depth = depth;
		this.pathCost = pathCost;
		
	}
}
