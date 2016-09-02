package edu.wpi.ntrowles.cs4313.proj1;

import java.io.File;
import java.io.IOException;

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
		if(problem.getSearchType() == "greedy"){
			//Run greedy search on problem
			GreedySearch gSearch = new GreedySearch();
			solution = gSearch.search(problem);
			
		} else if (problem.getSearchType() == "iterative"){
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
