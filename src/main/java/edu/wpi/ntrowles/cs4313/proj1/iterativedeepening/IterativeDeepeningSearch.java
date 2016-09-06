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

public class IterativeDeepeningSearch implements Search {
	

	public SolutionInfo search (Problem problem){
		double goalNum = problem.getGoalNum();
		double startTimeSec = Calendar.getInstance().getTimeInMillis()/1000.0;
		
		
		int nodesExpanded = 0;
	    int maxDepth;
		Problem curProblem = problem;
		SolutionInfo curSolution = new SolutionInfo();
		Solution bestSolution = new Solution(new ArrayList<String>(), Double.MAX_VALUE);
		for(maxDepth = 0; maxDepth < Integer.MAX_VALUE; maxDepth++){
			//Start time before you call each search
			double timeLeft = startTimeSec - Calendar.getInstance().getTimeInMillis()/1000.0 + curProblem.getMaxTime();
			GeneralSearch gnrSearch = new GeneralSearch();
			
			//Pass in a new problem object with ONLY the time changed
			curSolution = gnrSearch.search(new Problem(curProblem.getStartNum(), curProblem.getGoalNum(), timeLeft, curProblem.getOperators(), curProblem.getSearchType()), new IDSQueue(maxDepth));
			nodesExpanded += curSolution.getNodesExpanded();
			
			//if the current solution is better than the best solution, then it becomes the best
			if(Math.abs(goalNum - curSolution.getSolution().getEndNum()) < Math.abs(goalNum - bestSolution.getEndNum())){
				bestSolution = curSolution.getSolution();
			}
				
				
			//When the best solution is finally returned
			if(curSolution.getErrNum() == 0 || curSolution.getErrNum() == 1){
				return new SolutionInfo(bestSolution, problem.getStartNum(), goalNum, Calendar.getInstance().getTimeInMillis()/1000.0-startTimeSec, nodesExpanded, maxDepth, curSolution.getErrNum());
			}
			
			
			
			
			
		}
		return curSolution;
		
	}

}
