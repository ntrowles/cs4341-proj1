package edu.wpi.ntrowles.cs4313.proj1.beans;

import java.util.List;

/**
 * Contains the path and end state for a given solution.
 * 
 * @author ntrowles
 */
public class Solution {
	//member data
	protected List<String> path;   //path of solution
	protected double endNum;       //calculated number after path
	protected double startNum;     //number it begins with

	//getters and setters
	public List<String> getPath() {
		return path;
	}
	public void setPath(List<String> path) {
		this.path = path;
	}
	public double getEndNum() {
		return endNum;
	}
	public void setEndNum(double endNum) {
		this.endNum = endNum;
	}
	public double getStartNum(){
		return startNum;
	}
	
	public double calcEndNum(double startNum, List<String> aPathy){
		double newEnd = startNum;
		for(int i = 0; i < aPathy.size(); i++){
			char operator = aPathy.get(i).charAt(0);
			double number = Double.parseDouble(aPathy.get(i).substring(2));
			switch(operator){
				case '+':
					newEnd += number;
					break;
				case '-':
					newEnd -= number;
					break;
				case '*':
					newEnd *= number;
					break;
				case '/':
					newEnd /= number;
					break;
				case '^':
					newEnd = Math.pow(newEnd, number);
				    break;
				default:
					System.out.println( operator+ "is not an operator scrub Please use +,-,*,/,^");
			        break;
			}	
		}
		return newEnd;
	}
	//constructor
	public Solution(List<String> path, double endNum, double startNum){
		this.startNum = startNum;
		this.path = path;
		this.endNum = endNum;
	}
	public Solution(List<String> path, double endNum){
		this.path = path;
		this.endNum = endNum;
	}
}
