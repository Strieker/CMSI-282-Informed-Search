// Sage Strieker

package pathfinder.informed;

import java.util.*;


/**
 * Maze Pathfinding algorithm that implements informed, depth-first, A* search.
 */
public class Pathfinder {
    
	/**
     * Returns the path of necessary actions to be taken
     * from the initial to the Key to the nearest Goal, if the path exists. This is done by  calling the 
     * searchPath function twice: once in order to find the path from the Initial State in the maze to the Key State,
     * should such a  path exist, and then another time in order to find the path from the Key State to the nearest
     * Goal State. 
     *
     * @param A MazeProblem object, to provide the initial maze, the MazeState
     * of the Key, the Initial, and the list of Goal States, as well as the cost 
     * to get to the current MazeState to the Goal State
     * @return An ArrayList of actions to get from the Initial to the Goal State
     */
    public static ArrayList<String> solve (MazeProblem problem) {
    	if (problem.KEY_STATE == null) {
    		return null;
    	} else if (problem.GOAL_STATES.size()  == 0) {
    		return null;
    	}
    	ArrayList<String> path = new ArrayList<String>();
    	SearchTreeNode initial = new SearchTreeNode(problem.INITIAL_STATE, null, null, getHeuristic(problem.INITIAL_STATE, problem.KEY_STATE), 0);
    	MazeState GOAL_STATE = getClosestGoal(problem.KEY_STATE, problem);
    	SearchTreeNode key = new SearchTreeNode(problem.KEY_STATE, null, null, getHeuristic(problem.KEY_STATE, GOAL_STATE),0);
    	ArrayList<String> pathToKey = searchPath(problem,initial,problem.KEY_STATE);
    	if (pathToKey == null) {
    		return null;
    	} 
    	ArrayList<String> pathToGoal = searchPath(problem, key, GOAL_STATE);
    	if (pathToGoal == null) {
    		return null;
    	} 
    	System.out.println(pathToKey.toString());
    	System.out.println(pathToGoal.toString());
    	for (int i = 0; i < pathToGoal.size(); i++) {
    		pathToKey.add(pathToGoal.get(i));
    	}
    	path = pathToKey; 
    	return path;
    }
    
    /**
     * Returns the history of a given TreeNode object based on its parent's history 
     *
     * @param A MazeProblem object, the current's parent, in the form of a TreeNode, and the current's MazeState
     * @return An integer representation of the history
     */
    private static int getHistory(SearchTreeNode parent, MazeState current, MazeProblem problem) {
    	return parent.history + problem.getCost(current);
    }
    
    /**
     * A Comparator function that uses a lambda expression to compare two given tree nodes 
     * based on their evaluation cost; the purpose of this function is to evaluate how to properly order
     * added values in the frontier, which is defined as a priority queue in the later searchPath function
     */
    private static Comparator<SearchTreeNode> compare = (n1,n2) -> {
    	return n1.evaluate() - n2.evaluate();
    };
    
    /**
     * Given a leaf node in the search tree (a goal), returns a solution by traversing
     * up the search tree, collecting actions along the way, until reaching the root
     * 
     * @param last SearchTreeNode to start the upward traversal at (a goal node)
     * @return ArrayList sequence of actions; solution of format ["U", "R", "U", ...]
     */
    private static ArrayList<String> getPath (SearchTreeNode last) {
        ArrayList<String> result = new ArrayList<>();
        for (SearchTreeNode current = last; current.parent != null; current = current.parent) {
            result.add(current.action);
        }
        Collections.reverse(result);
        return result;
    }
   
    /**
     * Returns the heuristic cost of a given MazeState from its current position to a goal; 
     * this goal does not necessarily mean from the current position to one for the Goal States, it can also
     * be from the current position to a Key State, as well. This heuristic function is constructed based 
     * on the Manhattan Distance formula. 
     *
     * @param The MazeState of the current position in the maze, the MazeState of the goal
     * @return An integer representation of the history
     */
    private static int getHeuristic(MazeState current, MazeState goal) {
    	return Math.abs(current.col - goal.col) + Math.abs(current.row - goal.row);
    }
    
    /**
     * Given a MazeProblem, which specifies the actions and transitions available in the
     * search, returns a solution to the problem as a sequence of actions that leads from
     * a defined initial to a goal state. As it was in the case for the heuristic function,
     * this initial does not need to be the Initial State in the maze, and the goal does not need
     * to be one of the Goal States. In actuality, this function is called twice in solve. In the 
     * first case, the initial is set to the Initial State, an the goal, to the Key State. In the second case,
     * the initial is set to the Key State, and the goal to the nearest Goal State. 
     * 
     * @param problem A MazeProblem that specifies the maze, actions, transitions, a SearchTreeNode, initial,
     * that defines where to start  in the  maze, a goalType, which defines the MazeState for the goal
     * @return An ArrayList of Strings representing actions that lead from the initial to
     * the goal state, of the format: ["R", "R", "L", ...]
     */
    private static ArrayList<String> searchPath(MazeProblem problem, SearchTreeNode initial, MazeState goalType) {
    	if (problem.KEY_STATE == null) {
    		return null;
    	} else if (problem.GOAL_STATES.size()  == 0) {
    		return null;
    	}
    	PriorityQueue<SearchTreeNode> frontier = new PriorityQueue<>(compare);
    	Set<MazeState> graveyard = new HashSet<>();
    	frontier.add(initial);
    	System.out.println(Integer.toString(initial.state.col) +  Integer.toString(initial.state.row));
    	System.out.println();
        while (!frontier.isEmpty()) {
        	SearchTreeNode expanding = frontier.poll();
        	graveyard.add(expanding.state);
    		if(goalType == problem.KEY_STATE) {
    			if (problem.isKey(expanding.state)) {
    				return getPath(expanding);
                }
    		} else if(problem.GOAL_STATES.contains(goalType)) {
    			if (problem.isGoal(expanding.state)) {
    				return getPath(expanding);
                }
    		}
    		Map<String, MazeState> transitions = problem.getTransitions(expanding.state);
            for (Map.Entry<String, MazeState> transition : transitions.entrySet()) {
            	SearchTreeNode generated = new SearchTreeNode(
            		transition.getValue(), 
            		transition.getKey(), 
            		expanding, 
            		getHeuristic(expanding.state, goalType),
            		getHistory(expanding,transition.getValue(), problem)
            	);
            	System.out.println("---------");
        		System.out.println(Integer.toString(expanding.state.col) + "+" + Integer.toString(expanding.state.row));
        		System.out.println("history " + Integer.toString(getHistory(expanding,transition.getValue(), problem)));
        		System.out.println("heuristic " + Integer.toString(getHeuristic(expanding.state, goalType)));
            	System.out.println("---------");
            	if (!graveyard.contains(generated.state)){
            		frontier.add(generated);
            	}
            }
        }
        return null;
    }
    
    /**
     * Finds the closest goal in the list of Goal States based on the current state's history by calling 
     * its getCost function. 
     *  
     * @param problem A MazeProblem that specifies the maze, actions, transitions, and the current MazeState
     * @return An ArrayList of Strings representing actions that lead from the initial to
     * the goal state, of the format: ["R", "R", "L", ...]
     */
    public static MazeState getClosestGoal (MazeState current, MazeProblem problem) {
    	if (problem.GOAL_STATES.size() == 0) {
    		return null;
    	}
    	int lowestCost = getHeuristic(current, problem.GOAL_STATES.get(0)) + problem.getCost(current);
    	MazeState closestGoal = problem.GOAL_STATES.get(0); 
    	for (int i = 0; i < problem.GOAL_STATES.size(); i++) {
    		if(lowestCost > getHeuristic(current, problem.GOAL_STATES.get(i)) + problem.getCost(current)) {
    			lowestCost = getHeuristic(current, problem.GOAL_STATES.get(i)) + problem.getCost(current);
    			closestGoal = problem.GOAL_STATES.get(i);
    		}
    	}
    	return closestGoal;
    }
}

/**
 * SearchTreeNode that is used in the Search algorithm to construct the Search
 * tree.
 */
class SearchTreeNode {
    MazeProblem problem;
    MazeState state;
    String action;
    SearchTreeNode parent;
    int heuristic;
    int history;
    
    /**
     * Constructs a new SearchTreeNode to be used in the Search Tree.
     * 
     * @param state The MazeState (row, col) that this node represents.
     * @param action The action that *led to* this state / node.
     * @param parent Reference to parent SearchTreeNode in the Search Tree.
     * @param the heuristic cost of the current node. 
     * @param the  history cost of the current node.
     */
    SearchTreeNode (MazeState state, String action, SearchTreeNode parent, int heuristic, int history) {
        this.state = state;
        this.action = action;
        this.parent = parent;
        this.heuristic = heuristic;
        this.history = history;
    }
    
    /**
     * Returns the total evaluation cost of a given TreeNode object based 
     * on its given history and heuristic  costs.
     */
    public int evaluate() {
    	return history + heuristic;
    }
    
}