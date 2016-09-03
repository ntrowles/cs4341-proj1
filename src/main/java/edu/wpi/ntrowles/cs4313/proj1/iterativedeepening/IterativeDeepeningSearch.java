package edu.wpi.ntrowles.cs4313.proj1.iterativedeepening;

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
import edu.wpi.ntrowles.cs4313.proj1.utils.Queue;
import edu.wpi.ntrowles.cs4313.proj1.utils.Search;

public class IterativeDeepeningSearch implements Search {

	
	
	
	public SolutionInfo search(Problem moProblems) {
		int depth = 0;
		//for depth = 0 to infinity do
		//Result assigned Depth-Limited-Search (problem, depth)
		//If result != cutoff then return result
		return null;
	}
	
	private Solution DepthLimitedSearch (Problem problem, int limit){
		//return recursive DLS (Make-Node(problem.Initial-State),problem, limit)
		return null;
	}
	
	private Solution RecursiveDLS (Node node, Problem problem, int limit){
		//if problem.Goal-Test(node.State) then return Solution(node)
		//else if limit = 0 then return curoff
		//else
		//      cutoff_occurred? assigned false
		//		for each action in problem.Actions(node.State) do
		//			child assigned Child-Node(problem, node, action)
		//			result assigned Recursive-DLS(child, problem, limit-1)
		//			if result = cutoff then cutoff.occurred? assigned true
		//			else if result not != failure then return result
		//		if cutoff_occurred? then return cutoff else return failure
		return null;
	}

}
