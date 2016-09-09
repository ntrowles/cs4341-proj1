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
 * GreedySearch is the object that the user can use to find a path from
 * the starting number to the goal number in a specified amount of time 
 * using a specified set of operators using a Greedy Search algorithm.
 * 
 * @author ntrowles
 */
public class GreedySearch implements Search {

	/**
	 * Returns a solution SolutionInfo object containing all of the 
	 * information pertaining to the solution of a specified problem
	 * solved through the Greedy Search method.
	 * <p>
	 * The application of a Greedy Search method is accomplished through the
	 * use of a GreedySearchQueue object, a priority queue where higher 
	 * priority is placed on a Node with a numerical value closer to the 
	 * numerical value of the goal state of the problem.
	 * 
	 * @param problem	an object containing all the necessary information
	 * 					to perform a search
	 * @return	SolutionInfo object containing the information pertaining to
	 * 			the solution of the problem
	 */
	public SolutionInfo search(Problem problem){
		GeneralSearch gnrSearch = new GeneralSearch();
		return gnrSearch.search(problem, new GreedySearchQueue());
	}
}
