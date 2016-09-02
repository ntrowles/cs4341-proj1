package edu.wpi.ntrowles.cs4313.proj1;

import java.util.List;

public class Problem {
	private double startNum;
	private double endNum;
	private double maxTime;
	private List<String> operators;
	private String searchType;
	
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

	public double getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(double maxTime) {
		this.maxTime = maxTime;
	}

	public List<String> getOperators() {
		return operators;
	}

	public void setOperators(List<String> operators) {
		this.operators = operators;
	}
	
	public Problem(double startNum, double endNum, double maxTime, List<String> operators, String searchType){
		this.startNum = startNum;
		this.endNum = endNum;
		this.maxTime = maxTime;
		this.operators = operators;
		this.searchType = searchType;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		
		builder.append("Starting number: " + startNum + "\n");
		builder.append("Target number: " + endNum + "\n");
		builder.append("Maximum Time: " + maxTime + "\n");
		builder.append("Operators: " + operators.toString() + "\n");
		builder.append("Search type: " + searchType + "\n");
		
		return builder.toString();
	}
}
