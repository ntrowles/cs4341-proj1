package edu.wpi.ntrowles.cs4313.proj1.beans;

import java.util.List;

/**
 * Holds all information about the problem state. This information includes:
 * <ul>
 * <li> The numerical starting state 
 * <li> The numerical desired state
 * <li> The time limit for the search algorithm
 * <li> This list of allowed operators to change a state
 * <li> The type of search being run
 * </ul>
 * 
 * @author ntrowles
 */
public class Problem {
	//member data
	private double startNum;         //starting state
	private double goalNum;          //desired state
	private double maxTime;          //time to search for solution
	private List<String> operators;  //operators to change state
	private String searchType;       //type of search
	
	//getters and setters
	public double getStartNum() {
		return startNum;
	}
	public void setStartNum(double startNum) {
		this.startNum = startNum;
	}
	public double getGoalNum() {
		return goalNum;
	}
	public void setGoalNum(double goalNum) {
		this.goalNum = goalNum;
	}
	public double getMaxTime() {
		return maxTime;
	}
	public void setMaxTime(double maxTime) {
		this.maxTime = maxTime;
	}

	/**
	 * Contains the entire path so far
	 * 
	 * @return A list of strings representing operators 
	 * used so far.
	 */
	public List<String> getOperators() {
		return operators;
	}

	public void setOperators(List<String> operators) {
		this.operators = operators;
	}
	
	/**
	 * Constructor for the problem state
	 * 
	 * @param startNum Starting state.
	 * @param endNum Ending state.
	 * @param maxTime Time taken to go down a path.
	 * @param operators Path selection.
	 * @param searchType Path selector.
	 */
	public Problem(double startNum, double goalNum, double maxTime, List<String> operators, String searchType){
		this.startNum = startNum;
		this.goalNum = goalNum;
		this.maxTime = maxTime;
		this.operators = operators;
		this.searchType = searchType;
	}

	/**
	 * Used to determine if a greedy search or a 
	 * iterative deepening search will be used.
	 * 
	 * @return A string representing the search type.
	 */
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
		builder.append("Target number: " + goalNum + "\n");
		builder.append("Maximum Time: " + maxTime + "\n");
		builder.append("Operators: " + operators.toString() + "\n");
		builder.append("Search type: " + searchType + "\n");
		
		return builder.toString();
	}
}
