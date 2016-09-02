package edu.wpi.ntrowles.cs4313.proj1;

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
	public Solution search(Problem moProblems);
}
