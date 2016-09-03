package edu.wpi.ntrowles.cs4313.proj1.utils;

import edu.wpi.ntrowles.cs4313.proj1.beans.Problem;
import edu.wpi.ntrowles.cs4313.proj1.beans.Solution;
import edu.wpi.ntrowles.cs4313.proj1.beans.SolutionInfo;

/**
 * Generic search interface
 * @author bgsarkis
 *
 */
public interface Search {
	
	/**
	 * @author bgsarkis
	 * @param moProblems The input problem state to be dealt with.
	 * @return States that define a goal.
	 */
	public SolutionInfo search(Problem moProblems);
}
