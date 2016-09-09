package edu.wpi.ntrowles.cs4313.proj1.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import edu.wpi.ntrowles.cs4313.proj1.beans.Problem;

/**
 * ProblemParser is an object with the purpose of reading an input file containing
 * information that can be used to create a Problem object.
 * 
 * @author ntrowles
 */
public class ProblemParser {
	
	/**
	 * Reads the input file and creates a Problem object based on the
	 * information provided in this file.
	 * 
	 * @param pathString	a path to the location of the input file
	 * @return	a problem with information corresponding to the 
	 * 			information contained in the input file
	 * @throws IOException
	 */
	public Problem parse(String pathString) throws IOException{
		List<String> lines = Files.readAllLines(Paths.get(pathString));
		String startNumString = lines.get(1);
		String endNumString = lines.get(2);
		String maxTimeString = lines.get(3);
		List<String> operators = lines.subList(4, lines.size());
		String searchType = lines.get(0);
		
		double startNum = Double.parseDouble(startNumString);
		double endNum = Double.parseDouble(endNumString);
		double maxTime = Double.parseDouble(maxTimeString);
		
		Problem problem = new Problem(startNum, endNum, maxTime, operators, searchType);
		return problem;
	}
}
