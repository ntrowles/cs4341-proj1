package edu.wpi.ntrowles.cs4313.proj1.iterativedeepening;

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
		int nodesExpanded = 0;
	    int maxDepth;
		Problem curProblem = problem;
		SolutionInfo curSolution = new SolutionInfo();
		for(maxDepth = 0; maxDepth < Integer.MAX_VALUE; maxDepth++){
			//Start time before you call each search
			double timeLeft = curProblem.getMaxTime() - Calendar.getInstance().getTimeInMillis()/1000;
			GeneralSearch gnrSearch = new GeneralSearch();
			
			//Pass in a new problem object with ONLY the time changed
			curSolution = gnrSearch.search(new Problem(curProblem.getStartNum(), curProblem.getGoalNum(), timeLeft, curProblem.getOperators(), curProblem.getSearchType()), new IDSQueue(maxDepth));
			nodesExpanded += curSolution.getNodesExpanded();
			
			
		}
		return curSolution;
		
	}

}
