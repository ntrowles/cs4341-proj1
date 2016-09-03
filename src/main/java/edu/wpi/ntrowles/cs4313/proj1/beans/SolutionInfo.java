package edu.wpi.ntrowles.cs4313.proj1.beans;

import java.util.List;

public class SolutionInfo{
	private Solution solution;
	private double startNum;
	private double goalNum;
	private double timeToExec;
	private int nodesExpanded;
	private int maxSearchDepth;
	private String errorMessage;
	
	//Getters and Setters
	public double getTimeToExec() {
		return timeToExec;
	}
	public void setTimeToExec(double timeToExec) {
		this.timeToExec = timeToExec;
	}
	public int getNodesExpanded() {
		return nodesExpanded;
	}
	public void setNodesExpanded(int nodesExpanded) {
		this.nodesExpanded = nodesExpanded;
	}
	public int getMaxSearchDepth() {
		return maxSearchDepth;
	}
	public void setMaxSearchDepth(int maxSearchDepth) {
		this.maxSearchDepth = maxSearchDepth;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	//Constructor
	public SolutionInfo(Solution solution, double startNum, double goalNum, double timeToExec, int nodesExpanded, int maxSearchDepth, String errorMessage){
		this.solution = solution;
		this.startNum = startNum;
		this.goalNum = goalNum;
		this.timeToExec = timeToExec;
		this.nodesExpanded = nodesExpanded;
		this.maxSearchDepth = maxSearchDepth;
		this.errorMessage = errorMessage;
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		double leftOperand = startNum;
		
		//Print path to get to endNum
		List<String> path = solution.getPath();
		for(int i=0; i<path.size(); i++){
			builder.append("" + leftOperand + " "); //append left operand
			
			char operator = path.get(i).charAt(0);
			builder.append(operator + " "); //append operator
			
			String rightOperandString = path.get(i).substring(1);
			double rightOperand = Double.parseDouble(rightOperandString);
			builder.append(rightOperandString + " = "); //append right operator
			
			double solution = 0;
			switch(operator){
			case '+':
				solution = leftOperand + rightOperand; 
				break;
				
			case '-':
				solution = leftOperand - rightOperand;
				break;
				
			case '*':
				solution = leftOperand * rightOperand;
				break;
				
			case '/':
				solution = leftOperand / rightOperand;
				break;
				
			case '^':
				solution = Math.pow(leftOperand, rightOperand);
				break;
				
			default:
				System.out.println("Unable to process operator type: " + operator + "; Please use +,-,*,/,^");
				break;
			}
			builder.append("" + solution + "\n");
			
			leftOperand = solution;
		}
		builder.append("\n");
		//additional info to print
		builder.append("Error: " + errorMessage + "\n");
		builder.append("Number of steps required: " + path.size() + "\n");
		builder.append("Search required: " + timeToExec + " seconds\n");
		builder.append("Nodes expanded: " + nodesExpanded + "\n");
		builder.append("Maximum search depth: " + maxSearchDepth + "\n");
		
		return builder.toString();
	}
	public double getGoalNum() {
		return goalNum;
	}
	public void setGoalNum(double goalNum) {
		this.goalNum = goalNum;
	}
}
