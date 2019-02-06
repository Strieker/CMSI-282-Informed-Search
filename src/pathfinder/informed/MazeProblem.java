package pathfinder.informed;

import java.util.Map;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Specifies the Maze Grid pathfinding problem including the actions, transitions,
 * goal test, and solution test. Can be fed as an input to a Search algorithm to
 * find and then test a solution.
 */
public class MazeProblem {

    // Fields
    // -----------------------------------------------------------------------------
    private String[] maze;
    private int rows, cols;
    public final MazeState INITIAL_STATE, KEY_STATE;
    public ArrayList<MazeState> GOAL_STATES;
    private static final Map<String, MazeState> TRANS_MAP = createTransitions();

    /**
     * @return Creates the transition map that maps String actions to
     * MazeState offsets, of the format:
     * { "U": (0, -1), "D": (0, +1), "L": (-1, 0), "R": (+1, 0) }
     */
    private static final Map<String, MazeState> createTransitions () {
        Map<String, MazeState> result = new HashMap<>();
        result.put("U", new MazeState(0, -1));
        result.put("D", new MazeState(0,  1));
        result.put("L", new MazeState(-1, 0));
        result.put("R", new MazeState( 1, 0));
        return result;
    }

    // Constructor
    // -----------------------------------------------------------------------------
    /**
     * Constructs a new MazeProblem from the given maze; responsible for finding
     * the initial and goal states in the maze, and storing in the MazeProblem state.
     *
     * @param maze An array of Strings in which characters represent the legal maze
     * entities, including:<br>
     * 'X': A wall, 'G': A goal, 'I': The initial state, 'M': A mud tile, 'K': A key, '.': an open spot
     * For example, a valid maze might look like:
     * <pre>
     * String[] maze = {
     *    "XXXXXXX",
     *    "X....IX",
     *    "X..MXXX",
     *    "XGXKX.X",
     *    "XXXXXXX"
     * };
     * </pre>
     */
    MazeProblem (String[] maze) {
        this.maze = maze;
        this.rows = maze.length;
        this.cols = (rows == 0) ? 0 : maze[0].length();
        MazeState foundInitial = null, foundKey = null;
        ArrayList<MazeState> foundGoal = new ArrayList<MazeState>();
        // TODO FOUND KEY DEAL WITH HER 
        // TODO make goal an array
        // FIXME
        
        // Find the initial and goal state in the given maze, and then
        // store in fields once found
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                switch (maze[row].charAt(col)) {
                case 'I':
                    foundInitial = new MazeState(col, row); break;
                case 'G':
                	foundGoal.add(new MazeState(col, row)); break;
                case 'K':
                	foundKey = new MazeState(col, row); break;
                case 'M':
                case '.':
                case 'X':
                    break;
                default:
                    throw new IllegalArgumentException("Maze formatted invalidly");
                }
            }
        }
        GOAL_STATES = foundGoal;
        INITIAL_STATE = foundInitial;
        KEY_STATE = foundKey;
    }


    // Methods
    // -----------------------------------------------------------------------------

    /**
     * Returns whether or not the given state is a Goal state.
     *
     * @param state A MazeState (col, row) to test
     * @return Boolean of whether or not the given state is a Goal.
     */
    public boolean isGoal (MazeState state) {
        return GOAL_STATES.contains(state);
    }
    
    public boolean isKey (MazeState state) {
        return state.equals(KEY_STATE);
    }
    
    

    // Cost Function 
    // -----------------------------------------------------------------------------
    /**
     * Returns cost of an action depending on the type of tile is for the current 
     * position
     * 
     * The cost to move to a Goal state is 1 unit, the cost to move to an Initial state is 
     * 1 unit, the cost to move to a Mud tile is 3 units, the cost to move to a wall is not
     * applicable, since it is not possible to move to a wall, and the cost to move to a 
     * Key state is 1 unit
     * 
     * @param state A MazeState(col, row) to test
     * @return Integer equating to the total cost to move to the given tile 
     */
    public int getCost (MazeState state) {
    	//TODO cleanliness = switch 
    	char tileType = maze[state.row].charAt(state.col);
    	if (tileType == 'G' || tileType == 'K' || tileType == 'I' || tileType == '.') {
    		return 1;
    	}
        return 3;
    	
    }
    
    private static int getHeuristic(MazeState current, MazeProblem problem, MazeState goal) {
    	int x1 = current.col;
    	int y1 = current.row;
    	int x2 = goal.col;
    	int y2 = goal.row;
    	return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
    
    public MazeState getClosestGoal (MazeState state) {
    	int lowestCost = getHeuristic(state, this, GOAL_STATES.get(0)) + getCost(state);
    	MazeState closestGoal = GOAL_STATES.get(0); 
    	for (int i = 0; i < GOAL_STATES.size(); i++) {
    		if(lowestCost > getHeuristic(state, this, GOAL_STATES.get(i)) + getCost(state)) {
    			lowestCost = getHeuristic(state, this, GOAL_STATES.get(i)) + getCost(state);
    			closestGoal = GOAL_STATES.get(i);
    		}
    	}
    	return closestGoal;
    }
    
    /**
     * Returns a map of the states that can be reached from the given input
     * state using any of the available actions.
     *
     * @param state A MazeState (col, row) representing the current state
     * from which actions can be taken
     * @return Map A map of actions to the states that they lead to, of the
     * format, for current MazeState (c, r):<br>
     * { "U": (c, r-1), "D": (c, r+1), "L": (c-1, r), "R": (c+1, r) }
     */
    public Map<String, MazeState> getTransitions (MazeState state) {
        // Store transitions as a Map between actions ("U", "D", ...) and
        // the MazeStates that they result in from state
        Map<String, MazeState> result = new HashMap<>();

        // For each of the possible directions (stored in TRANS_MAP), test
        // to see if it is a valid transition
        // QUEUE IS NOT A CONCRETE IMPLEMENTATION OF A QUEUE IT'S AN INTERFACE
            // often a linked-list uses the queue's behaviour
            // Queue<String> queueueuue = new LinkedList<>();
            // format: Interface<Type> name = new Implementation<// if want same type>();
        // recall maps allow us to map unique keys to values for example, for transitions
        // if you want to map a string to a maze state, we are mapping the actions to the transitions
        // or the next states that they lead to
        // { "U": (c, r-1), "D": (c, r+1), "L": (c-1, r), "R": (c+1, r) }
        // recall syntax where you do for (type iterator : someCollection){} is a for each situation
        // this lets you iterate through the actual items stored in that collection
        // eg int[] arr = {1,2,3};
        // for (int i : arr){System.out.println(i);}
        // Entry is an internal class within map

        for (Map.Entry<String, MazeState> action : TRANS_MAP.entrySet()) {
            MazeState actionMod = action.getValue(),
                      newState  = new MazeState(state.col, state.row);
            newState.add(actionMod);

            // If the given state *is* a valid transition (i.e., within
            // map bounds and no wall at the position)...
            if (newState.row >= 0 && newState.row < rows &&
                newState.col >= 0 && newState.col < cols &&
                maze[newState.row].charAt(newState.col) != 'X') {
                // ...then add it to the result!
                result.put(action.getKey(), newState);
            }
        }
        return result;
    }
    /**
     * Given a possibleSoln, tests to ensure that it is indeed a solution to this MazeProblem,
     * as well as returning the cost.
     * 
     * @param possibleSoln A possible solution to test, which is a list of actions of the format:
     * ["U", "D", "D", "L", ...]
     * @return A 2-element array of ints of the format [isSoln, cost] where:
     * isSoln will be 0 if it is not a solution, and 1 if it is
     * cost will be an integer denoting the cost of the given solution to test optimality
     */
    public int[] testSolution (ArrayList<String> possibleSoln) {
        // Update the "moving state" that begins at the start and is modified by the transitions
        MazeState movingState = new MazeState(INITIAL_STATE.col, INITIAL_STATE.row);
        int cost = 0;
        boolean hasKey = false;
        int[] result = {0, -1};
        
        // For each action, modify the movingState, and then check that we have landed in
        // a legal position in this maze
        for (String action : possibleSoln) {
            MazeState actionMod = TRANS_MAP.get(action);
            movingState.add(actionMod);
            switch (maze[movingState.row].charAt(movingState.col)) {
            case 'X':
                return result;
            case 'K':
                hasKey = true; break;
            }
            cost += getCost(movingState);
        }
        result[0] = isGoal(movingState) && hasKey ? 1 : 0;
        result[1] = cost;
        return result;
    }
}