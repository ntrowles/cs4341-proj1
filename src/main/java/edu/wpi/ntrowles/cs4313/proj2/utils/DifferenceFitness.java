package edu.wpi.ntrowles.cs4313.proj2.utils;

import edu.wpi.ntrowles.cs4313.proj1.beans.Problem;
import edu.wpi.ntrowles.cs4313.proj1.beans.Solution;

public class DifferenceFitness implements Fitness {

	public double evaluateFitness(Solution sol, Problem prob) {
		return Math.abs(sol.getEndNum() - prob.getGoalNum());
	}

}
