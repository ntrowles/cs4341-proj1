package edu.wpi.ntrowles.cs4313.proj1;

import java.util.List;

public class Solution {
	private List<String> path;   //path of solution
	private double calcNum;      //calculated number after path
	private double startNum;
	private double endNum;
	
	private double timeToExec;
	private int nodesExpanded;
	private int maxSearchDepth;
	private String errorMessage;
	
	public Solution(List<String> path, double calcNum, double startNum, double endNum, double timeToExec, int nodesExpanded, int maxSearchDepth, String errorMessage){
		this.path = path;
		this.calcNum = calcNum;
		this.startNum = startNum;
		this.endNum = endNum;
		this.timeToExec = timeToExec;
		this.nodesExpanded = nodesExpanded;
		this.maxSearchDepth = maxSearchDepth;
		this.errorMessage = errorMessage;
	}

	public List<String> getPath() {
		return path;
	}

	public void setPath(List<String> path) {
		this.path = path;
	}

	public double getCalcNum() {
		return calcNum;
	}

	public void setCalcNum(double calcNum) {
		this.calcNum = calcNum;
	}

	public double getStartNum() {
		return startNum;
	}

	public void setStartNum(double startNum) {
		this.startNum = startNum;
	}

	public double getEndNum() {
		return endNum;
	}

	public void setEndNum(double endNum) {
		this.endNum = endNum;
	}

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
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		double leftOperand = startNum;
		for(int i=0; i<path.size(); i++){
			builder.append("" + leftOperand + " "); //append left operand
			
			char operator = path.get(i).charAt(0);
			builder.append(operator + " "); //append operator
			
			String rightOperandString = path.get(i).substring(1);
			double rightOperand = Double.parseDouble(rightOperandString);
			builder.append(rightOperandString + " = "); //append right operator
			
			switch(operator){
			case '+':
				break;
			case '-':
				break;
			}
		}
		
		return builder.toString();
	}
	
}
