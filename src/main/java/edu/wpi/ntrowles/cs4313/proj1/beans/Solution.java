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
	public Solution(double startNum, List<String> path){
		this.startNum = startNum;
		this.path = path;
		this.endNum = calcEndNum(startNum, path);
	}
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		double leftOperand = startNum;
		
		//Print path to get to endNum
		List<String> path = this.getPath();
		for(int i=0; i<path.size(); i++){
			builder.append("" + leftOperand + " "); //append left operand
			
			char operator = path.get(i).charAt(0);
			builder.append(operator + " "); //append operator
			
			String rightOperandString = path.get(i).substring(1);
			double rightOperand = Double.parseDouble(rightOperandString);
			builder.append("" + rightOperand + " = "); //append right operator
			
			double solution = 0;
			switch(operator){
			case '+':
				solution = (double)(leftOperand + rightOperand); 
				break;
				
			case '-':
				solution = (double)(leftOperand - rightOperand);
				break;
				
			case '*':
				solution = (double)(leftOperand * rightOperand);
				break;
				
			case '/':
				solution = (double)(leftOperand / rightOperand);
				break;
				
			case '^':
				solution = (double)(Math.pow(leftOperand, rightOperand));
				break;
				
			default:
				System.out.println("Unable to process operator type: " + operator + "; Please use +,-,*,/,^");
				break;
			}
			builder.append("" + solution + "\n");
			
			leftOperand = solution;
		}
		return builder.toString();
	}
}
