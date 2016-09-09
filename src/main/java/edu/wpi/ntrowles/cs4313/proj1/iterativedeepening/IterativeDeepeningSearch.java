package edu.wpi.ntrowles.cs4313.proj1.iterativedeepening;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * 
 * @author bgsarkis
 * @author Timothy Whitworth
 * Class containing the Iterative Deepening Search uninformed search method.
 *
 */

import java.util.LinkedList;

import edu.wpi.ntrowles.cs4313.proj1.beans.Node;
import edu.wpi.ntrowles.cs4313.proj1.beans.Problem;
import edu.wpi.ntrowles.cs4313.proj1.beans.Solution;
import edu.wpi.ntrowles.cs4313.proj1.beans.SolutionInfo;
import edu.wpi.ntrowles.cs4313.proj1.utils.GeneralSearch;
import edu.wpi.ntrowles.cs4313.proj1.utils.Queue;
import edu.wpi.ntrowles.cs4313.proj1.utils.Search;
/**
 * IterativeDeepingSearch is the object that the user can use to find a path from
 * the starting number to the goal number in a specified amount of time 
 * using a specified set of operators using a Iterative Deepening Search algorithm.
 * 
 * @author ntrowles
 */
public class IterativeDeepeningSearch implements Search {
	
	/**
	 * Returns a solution SolutionInfo object containing all of the 
	 * information pertaining to the solution of a specified problem
	 * solved through the Iterative Deepening Search method.
	 * <p>
	 * The application of an Iterative Deepening Search method is accomplished
	 * through the use of a IDSQueue object, FIFO queue. The search will always
	 * produce an optimal path because it searching every branch in each layer
	 * of the tree, which will result in the search terminating at the lowest
	 * possible depth.
	 * <p>
	 * The best solution is constantly updated, being set equal to the current 
	 * solution only when the current solution's end number is closer to the
	 * goal number. 
	 * 
	 * @param problem	an object containing all the necessary information
	 * 					to perform a search
	 * @return	SolutionInfo object containing the information pertaining to
	 * 			the solution of the problem
	 */
	public SolutionInfo search (Problem problem){
		double goalNum = problem.getGoalNum();
		double startTimeSec = (double) Calendar.getInstance().getTimeInMillis()/1000.0;
		
		
		int nodesExpanded = 0;
	    int maxDepth;
		Problem curProblem = problem;
		SolutionInfo curSolution = new SolutionInfo();
		Solution bestSolution = new Solution(new ArrayList<String>(), Double.MAX_VALUE);
		for(maxDepth = 0; maxDepth < Integer.MAX_VALUE; maxDepth++){
			//Start time before you call each search, time buffer of 0.05 of a second.
			double timeLeft = (double) Calendar.getInstance().getTimeInMillis()/1000 - startTimeSec  + curProblem.getMaxTime();
			GeneralSearch gnrSearch = new GeneralSearch();
			
			//Pass in a new problem object with ONLY the time changed
			curProblem = new Problem(curProblem.getStartNum(), curProblem.getGoalNum(), timeLeft, curProblem.getOperators(), curProblem.getSearchType());
			curSolution = gnrSearch.search(curProblem, new IDSQueue(maxDepth));
			nodesExpanded += curSolution.getNodesExpanded();
			
			//if the current solution is better than the best solution, then it becomes the best
			if(Math.abs(goalNum - curSolution.getSolution().getEndNum()) < Math.abs(goalNum - bestSolution.getEndNum())){
				bestSolution = curSolution.getSolution();
			}
				
				
			//When the best solution is finally returned
			if(curSolution.getErrNum() == 0 || curSolution.getErrNum() == 1){
				return new SolutionInfo(bestSolution, problem.getStartNum(), goalNum, curSolution.getTimeToExec(), nodesExpanded, maxDepth, curSolution.getErrNum());
			}
		}
		return curSolution;
		
	}

}
