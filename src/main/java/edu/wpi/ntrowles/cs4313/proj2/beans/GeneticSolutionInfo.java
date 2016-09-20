package edu.wpi.ntrowles.cs4313.proj2.beans;

import java.util.List;

import edu.wpi.ntrowles.cs4313.proj1.beans.Solution;
import edu.wpi.ntrowles.cs4313.proj1.beans.SolutionInfo;

public class GeneticSolutionInfo extends SolutionInfo {
	
	/**
	 * Constructor for GeneticSolutionInfo, meant for output.
	 * @param sol Solution of interest
	 * @param goalNum The number to get as close to as possible
	 * @param timeToExec Elapsed time.
	 * @param popSize Number of organisms.
	 * @param numGenerations Iterations of the search.
	 * @param errNum Kind of error, if any the search encounters.
	 */
	public GeneticSolutionInfo(Solution sol,  double goalNum, double timeToExec, int popSize, int numGenerations, int errNum){
		super(sol, sol.getStartNum(), goalNum, timeToExec, popSize, numGenerations, errNum);
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
			builder.append("" + rightOperand + " = "); //append right operator
			
			double solution = 0.0;
			switch(operator){
			case '+':
				solution = (leftOperand + rightOperand); 
				break;
				
			case '-':
				solution = (leftOperand - rightOperand);
				break;
				
			case '*':
				solution = (leftOperand * rightOperand);
				break;
				
			case '/':
				solution = (leftOperand / rightOperand);
				break;
				
			case '^':
				solution = (Math.pow(leftOperand, rightOperand));
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
		String errorMessage;
		switch (errNum){
		case 0:
			errorMessage = "" + errNum;
			break;
		case 1:
			errorMessage = "" + errNum + ": Search terminated early due to time constraints, no solution found";
			break;
		case 2:
			errorMessage = "" + errNum + ": Search is exhausted, no solution found";
			break;
		default:
			errorMessage = "" + errNum + ": Error number not understood";
			break;
		}
		
		builder.append("Error: " + errorMessage + "\n");
		builder.append("Number of steps required: " + path.size() + "\n");
		builder.append("Search required: " + timeToExec + " seconds\n");
		builder.append("Population size: " + nodesExpanded + "\n");
		builder.append("Number of Generations: " + (maxSearchDepth + 1) + "\n");
		
		return builder.toString();
	}
}
