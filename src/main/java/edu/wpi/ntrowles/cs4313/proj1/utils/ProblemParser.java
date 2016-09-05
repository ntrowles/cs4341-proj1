package edu.wpi.ntrowles.cs4313.proj1.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import edu.wpi.ntrowles.cs4313.proj1.beans.Problem;

/**
 * 
 * @author ntrowles
 * 
 * []
 */
public class ProblemParser {
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
