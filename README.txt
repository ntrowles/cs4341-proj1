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
Both the Iterative Deepening and Greedy search algorithms make use of the General Search as
defined in the textbook (page 73). The General Search algorithm is implemented in the search()
method in GeneralSearch. This method takes a Problem and a Queue. In this case, a Queue is an
interface with an enqueue() method, which places new elements into the queue, a pop() method to remove
and return the first element of the queue, and isEmpty() to return whether or not the queue has any
elements left in it.

The general search loop through every element contained in the queue (in this case, Nodes), until
a solution is found, the queue is empty, or until time runs out. In each pass through of the loop,
the program will check if it is almost out of time, and return the best solution it found if it has.
If not, the search will then remove the Node at the front of the Queue, and check if it is better than
the current best solution found. If it is, it becomes the best solution. The program then checks if
the node represents a goal state, and returns it if it is. If it's not, the program then expands the
current Node and adds the resulting child nodes to the queue based on the enqueue() method. Finally, if
the search has been exhausted without finding a proper solution, the program will return the best
solution it could find.

The GreedySearch search() method simply calls the GeneralSearch search() method, and passes it a
GreedySearchQueue, which implements the Queue interface described above. The enqueue() method places
new nodes in order based on the difference between the current state and goal state (this is the
heuristic function used).

The IterativeDeepeningSearch search() method behaves a bit differently; it loops through calling the
GeneralSearch search() method, incrementing the depth limit by 1 each time, and decreasing the time
available based on how much time has elapsed since the search started. The depth limit
is enforced by the IterativeDeepeningQueue passed to the general search method. IterativeDeepeningQueue
takes a depth limit upon construction, and its enqueue() method does not allow new nodes to enter the
queue that have a depth greater than the depth limit. Otherwise, the queue acts as a depth-first search
queue; it enqueues new nodes a the front. Once the general search has occurred for the given
depth limit, the program will check if the solution is better than the current best solution, and replace
it if it is. Next the program will check if the best solution is valid or was terminated due to a time 
constraint. If it was, it will return the best solution, otherwise, the search will continue, and general
search will be called again with a depth limit incremented by 1.
		
		