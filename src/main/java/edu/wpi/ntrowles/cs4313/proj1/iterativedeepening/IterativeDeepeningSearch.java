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
import edu.wpi.ntrowles.cs4313.proj1.utils.GeneralSearch;
import edu.wpi.ntrowles.cs4313.proj1.utils.Queue;
import edu.wpi.ntrowles.cs4313.proj1.utils.Search;

public class IterativeDeepeningSearch implements Search {
	
	
	public SolutionInfo search (Problem problem){
		GeneralSearch gnrSearch = new GeneralSearch();
		return gnrSearch.search(problem, new IDSQueue());
	}

}
