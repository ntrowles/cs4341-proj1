# Overview #

## Authors ##
Nicholas Rowles
Benjamin Sarkis
Tommy Trieu		
Timothy Whitworth	

## Documentation ##
This program is meant to solve the problem presented in assignment 1 for CS4313 at Worcester
Polytechnic Institute. For this problem, the program had to read in a search problem, and then
execute an iterative deepening search or greedy search on the problem, depending on which method
it specified.

To use this program, make sure that you have java installed. call 
	java -jar cs4313-proj1.jar /path/to/file to run this program.

## How it Works ##
The main() function is contained within the Main class. To program will first read in the problem
from the file specified in argument 1 (/path/to/file). To do so, it will create a ProblemParser
object, and will invoke its parse() method, passing it the file path (/path/to/file). This method
will return a Problem object, which contains information about the problem; the start state, goal
state, a list of operators, time to solve, and the type of search to execute. This information is
then printed to the console.

The program will then check the type of search to execute, and create the appropriate search object,
either a GreedySearch or an IterativeDeepeningSearch, and then call that object's search funtion(),
passing it a Problem object (the one created by the parse() method), and getting back a SolutionInfo
object. SolutionInfo contains all required information about the solution:
	* Solution that the search found, which is made up of an end state, and the path to get there
		*NOTE* Solution is not guaranteed to be correct, a search may return the closest it could
			   find if it runs out of time or exhausts the search space and cannot find a valid
			   solution
	* Starting and Goal States from problem
	* Actual runtime of the search (wall-clock time)
	* Nodes Expanded during the search
	* Maximum search depth of the search
	* Error number:
		* 0: no error, valid solution is returned
		* 1: ran out of time, best solution found in alloted time is returned
		* 2: search space is exhausted, no valid solution, best solution is returned

The information in solution info is then printed out to console, and the program then terminates.

## Searches ##

		
		