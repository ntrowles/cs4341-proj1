package edu.wpi.ntrowles.cs4313.proj1.beans;

/**
 * 
 * @author ntrowles
 *
 * Nodes are used by searches to represent a state, and to told on to all
 * necessary information about how the search reached this state
 */
public class Node {
	//member data
	private double state;     //curent value
	private Node parent;      //parent node
	private String operator;  //operator used to get here from parent node
	private int depth;        //depth of node
	private double pathCost;  //cost to get from root node to this node
	
	//getters and setters
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
	
	//Constructor
	public Node(double state, Node parent, String operator, int depth, double pathCost){
		this.state = state;
		this.parent = parent;
		this.operator = operator;
		this.depth = depth;
		this.pathCost = pathCost;
	}
	
}
