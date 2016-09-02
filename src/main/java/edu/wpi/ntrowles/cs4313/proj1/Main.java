package edu.wpi.ntrowles.cs4313.proj1;

import java.io.File;
import java.io.IOException;

import edu.wpi.ntrowles.cs4313.proj1.beans.Problem;
import edu.wpi.ntrowles.cs4313.proj1.beans.Solution;
import edu.wpi.ntrowles.cs4313.proj1.greedy.GreedySearch;
import edu.wpi.ntrowles.cs4313.proj1.iterativedeepening.IterativeDeepeningSearch;
import edu.wpi.ntrowles.cs4313.proj1.utils.ProblemParser;

public class Main {

	public static void main(String[] args) {
		System.out.println("====ntrowles==bjsarkis==ttrieu==twitworth====");
		
		//Parse input from logs
		ProblemParser pars = new ProblemParser();
		Problem problem;
		try {
			problem = pars.parse(args[0]);
		} catch (IOException e) {
			System.out.println("Unable to load information from file located at: " + args[0]);
			e.printStackTrace();
			return;
		}
		System.out.println("------Problem-------");
		System.out.println(problem.toString());
		System.out.println("--------------------\n");
		
		Solution solution;
		//Check which type of search to run
		if(problem.getSearchType().equals("greedy")){
			//Run greedy search on problem
			GreedySearch gSearch = new GreedySearch();
			solution = gSearch.search(problem);
			
		} else if (problem.getSearchType().equals("iterative")){
			//Run iterative deepening search on problem
			IterativeDeepeningSearch idSearch = new IterativeDeepeningSearch();
			solution = idSearch.search(problem);
			
		} else {
			System.out.println("Unable to determine search to execute: " + problem.getSearchType() + "; use 'greedy' or 'iterative'");
			return;
		}

		//Print solution
		System.out.println("------Solution------");
		System.out.println(solution.toString());
		System.out.println("--------------------");
		
		
		System.out.println("=============================================");
	}

}
