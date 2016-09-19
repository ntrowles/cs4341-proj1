package edu.wpi.ntrowles.cs4313.proj2.utils;

import edu.wpi.ntrowles.cs4313.proj1.beans.Problem;
import edu.wpi.ntrowles.cs4313.proj1.beans.Solution;

public class DifferenceFitness implements Fitness {

	/**
	 * The heuristic function modeled as a fitness function.
	 * @param sol Solution being evaluated.
	 * @param prob Used for the goal number
	 * @return Closeness to goal
	 */
	public double evaluateFitness(Solution sol, Problem prob) {
		return Math.abs(sol.getEndNum() - prob.getGoalNum());
	}

}
