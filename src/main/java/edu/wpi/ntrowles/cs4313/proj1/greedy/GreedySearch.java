package edu.wpi.ntrowles.cs4313.proj1.greedy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import edu.wpi.ntrowles.cs4313.proj1.beans.Node;
import edu.wpi.ntrowles.cs4313.proj1.beans.Problem;
import edu.wpi.ntrowles.cs4313.proj1.beans.Solution;
import edu.wpi.ntrowles.cs4313.proj1.beans.SolutionInfo;
import edu.wpi.ntrowles.cs4313.proj1.utils.GeneralSearch;
import edu.wpi.ntrowles.cs4313.proj1.utils.Search;

/**
 * 
 * 
 * @author ntrowles
 */
public class GreedySearch implements Search {

	/**
	 * 
	 * 
	 * @param problem	
	 */
	public SolutionInfo search(Problem problem){
		GeneralSearch gnrSearch = new GeneralSearch();
		return gnrSearch.search(problem, new GreedySearchQueue());
	}
}
