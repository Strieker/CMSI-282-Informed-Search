package pathfinder.informed;

import java.util.*;


/**
 * Maze Pathfinding algorithm that implements informed, depth-first, A* search.
 */
// MADE A SEARCH METHOD TO FIX THE ISSUE, SINCE BREAKING IT UP INTO 2 METHODS FOR FOUNDKEY AND FOUNDGOAL WAS
// A TON OF REPEATED CODE AND IT WAS COMPLETELY GIVING ME THE WRONG ANSWERS BUT NOW FOR SOLVE PATH ISN'T UPDATING 
// IM RETURNING A TREE NODE AND TRYING TO UPDATE THE PATH 
// I THINK IT'S NOT DEAL WITH WALLS 
// AND ALSO I THINK IT'SFINDING THE KEY BUT STARTING FROM THE WRONG PLACE AFTER THAT 
public class Pathfinder {
    
    public static ArrayList<String> solve (MazeProblem problem) {
    	ArrayList<String> path = new ArrayList<String>();
    	SearchTreeNode initial = new SearchTreeNode(problem.INITIAL_STATE, null, null, getHeuristic(problem.INITIAL_STATE, problem, problem.KEY_STATE), 0);
    	// THE KEY 
    	// HOW TO HANDLE THE CLOSEST GOAL 
    	SearchTreeNode key = new SearchTreeNode(problem.KEY_STATE, null, null, getHeuristic(problem.KEY_STATE, problem, problem.GOAL_STATE),0);
    	ArrayList<String> pathToKey = searchPath(problem,initial,problem.KEY_STATE);
    	if (pathToKey == null) {
    		return null;
    	} 
    	ArrayList<String> pathToGoal = searchPath(problem, key, problem.GOAL_STATE);
    	if (pathToGoal == null) {
    		return null;
    	} 
    	System.out.println(pathToKey.toString());
    	// MAKE A PROPER SEARCH TREE NODE FOR JUST THE KEY 
    	// IF YOU STORE THE GOALS INTO A SET, THEN THE HEURISTIC CAN FIGURE OUT WHAT THE CLOSEST GOAL IS 
    	System.out.println(pathToGoal.toString());
    	for (int i = 0; i < pathToGoal.size(); i++) {
    		pathToKey.add(pathToGoal.get(i));
    	}
    	path = pathToKey; 
    	return path;
    }
    
    private static int getHistory(SearchTreeNode parent, MazeState current, MazeProblem problem) {
    	return parent.history + problem.getCost(current);
    }

    
    
    private static Comparator<SearchTreeNode> compare = (n1,n2) -> {
    	return n1.evaluate() - n2.evaluate();
    };
    
    private static ArrayList<String> getPath (SearchTreeNode last) {
        ArrayList<String> result = new ArrayList<>();
        for (SearchTreeNode current = last; current.parent != null; current = current.parent) {
            result.add(current.action);
        }
        Collections.reverse(result);
        return result;
    }
   
   // TODO kill problem as a parameter for getHeuristic 
    private static int getHeuristic(MazeState current, MazeProblem problem, MazeState goal) {
    	int x1 = current.col;
    	int y1 = current.row;
    	int x2 = goal.col;
    	int y2 = goal.row;
    	return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
    
    // TODO refactor in mazeproblem look at the portion where we are going cell by cell 
    // to handle returning the proper node 
    private static SearchTreeNode searchNode(MazeProblem problem, SearchTreeNode initial, MazeState goalType) {
    	PriorityQueue<SearchTreeNode> frontier = new PriorityQueue<>(compare);
    	Set<MazeState> foundValues = new HashSet<>();
    	frontier.add(initial);
        while (!frontier.isEmpty()) {
        	SearchTreeNode expanding = frontier.poll();
    		foundValues.add(expanding.state);
    		if(goalType == problem.KEY_STATE) {
    			if (problem.isKey(expanding.state)) {
    				return expanding;
                }
    		} else if(goalType == problem.GOAL_STATE) {
    			if (problem.isGoal(expanding.state)) {
    				return expanding;
                }
    		}
    		Map<String, MazeState> transitions = problem.getTransitions(expanding.state);
            for (Map.Entry<String, MazeState> transition : transitions.entrySet()) {
            	SearchTreeNode generated = new SearchTreeNode(
            		transition.getValue(), 
            		transition.getKey(), 
            		expanding, 
            		getHeuristic(expanding.state, problem, goalType),
            		getHistory(expanding,transition.getValue(), problem)
            	);
            	System.out.println("---------");
        		System.out.println(Integer.toString(expanding.state.col) + "+" + Integer.toString(expanding.state.row));
        		System.out.println(getHistory(expanding,transition.getValue(), problem));
        		System.out.println(getHeuristic(expanding.state, problem, goalType));
            	System.out.println("---------");
            	if (!foundValues.contains(generated.state)){
            		frontier.add(generated);
            	}
            }
        }
        return null;
    }
    
    private static ArrayList<String> searchPath(MazeProblem problem, SearchTreeNode initial, MazeState goalType) {
    	PriorityQueue<SearchTreeNode> frontier = new PriorityQueue<>(compare);
    	Set<MazeState> foundValues = new HashSet<>();
    	frontier.add(initial);
        while (!frontier.isEmpty()) {
        	SearchTreeNode expanding = frontier.poll();
    		foundValues.add(expanding.state);
    		if(goalType == problem.KEY_STATE) {
    			if (problem.isKey(expanding.state)) {
    				return getPath(expanding);
                }
    		} else if(goalType == problem.GOAL_STATE) {
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
            		getHeuristic(expanding.state, problem, goalType),
            		getHistory(expanding,transition.getValue(), problem)
            	);
            	System.out.println("---------");
        		System.out.println(Integer.toString(expanding.state.col) + "+" + Integer.toString(expanding.state.row));
        		System.out.println(getHistory(expanding,transition.getValue(), problem));
        		System.out.println(getHeuristic(expanding.state, problem, goalType));
            	System.out.println("---------");
            	if (!foundValues.contains(generated.state)){
            		frontier.add(generated);
            	}
            }
        }
        return null;
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
//    int history;
    int heuristic;
    int history;
    
    /**
     * Constructs a new SearchTreeNode to be used in the Search Tree.
     * 
     * @param state The MazeState (row, col) that this node represents.
     * @param action The action that *led to* this state / node.
     * @param parent Reference to parent SearchTreeNode in the Search Tree.
     */
    
    public int evaluate() {
    	return history + heuristic;
    }
    
    
    SearchTreeNode (MazeState state, String action, SearchTreeNode parent, int heuristic, int history) {
        this.state = state;
        this.action = action;
        this.parent = parent;
        this.heuristic = heuristic;
        this.history = history;
    }
    
}