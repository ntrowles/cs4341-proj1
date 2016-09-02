package edu.wpi.ntrowles.cs4313.proj1.beans;

public class Node {
	private double state; //curent value
	private Node parent;  //parend node
	private String operator;
	private int depth;
	public double getState() {
		return state;
	}

	public void setState(double state) {
		this.state = state;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public double getPathCost() {
		return pathCost;
	}

	public void setPathCost(double pathCost) {
		this.pathCost = pathCost;
	}

	private double pathCost;
	
	public Node(double state, Node parent, String operator, int depth, double pathCost){
		this.state = state;
		this.parent = parent;
		this.operator = operator;
		this.depth = depth;
		this.pathCost = pathCost;
	}
	
}
