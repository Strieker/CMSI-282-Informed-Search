// Sage Strieker

package pathfinder.informed;

import java.util.*;

/**
 * Maze Pathfinding algorithm that implements informed, depth-first, A* search.
 */
public class Pathfinder {

	/**
	 * Returns the path of necessary actions to be taken from the initial to the Key
	 * to the nearest Goal, if the path exists. This is done by calling the
	 * searchPath function twice: once in order to find the path from the Initial
	 * State in the maze to the Key State, should such a path exist, and then
	 * another time in order to find the path from the Key State to the nearest Goal
	 * State.
	 * 
	 * @param problem MazeProblem object, to provide the initial maze, the MazeState
	 *                of the Key, the Initial, and the list of Goal States, as well
	 *                as the cost to get to the current MazeState to the Goal State
	 * @return An ArrayList of actions to get from the Initial to the Goal State
	 */
	public static ArrayList<String> solve(MazeProblem problem) {
		if (problem.KEY_STATES.size() == 0) {
			return null;
		} else if (problem.GOAL_STATES.size() == 0) {
			return null;
		}
		ArrayList<String> path = new ArrayList<String>();
		MazeState keyState = new MazeState(0, 0);
		SearchTreeNode initial = new SearchTreeNode(problem.INITIAL_STATE, null, null,
				getHeuristic(problem.INITIAL_STATE, problem.KEY_STATES), 0);
		for (MazeState key : problem.KEY_STATES) {
			keyState = key;
		}
		SearchTreeNode key = new SearchTreeNode(keyState, null, null, getHeuristic(keyState, problem.GOAL_STATES), 0);
		ArrayList<String> pathToKey = searchPath(problem, initial, problem.KEY_STATES);
		if (pathToKey == null) {
			return null;
		}
		ArrayList<String> pathToGoal = searchPath(problem, key, problem.GOAL_STATES);
		if (pathToGoal == null) {
			return null;
		}
		for (int i = 0; i < pathToGoal.size(); i++) {
			pathToKey.add(pathToGoal.get(i));
		}
		path = pathToKey;
		return path;
	}

	/**
	 * Returns the history of a given TreeNode object based on its parent's history
	 * 
	 * @param A MazeProblem object, the current's parent, in the form of a TreeNode,
	 *          and the current's MazeState
	 * @return An integer representation of the history
	 */
	private static int getHistory(SearchTreeNode parent, MazeState current, MazeProblem problem) {
		return parent.history + problem.getCost(current);
	}

	/**
	 * A Comparator function that uses a lambda expression to compare two given tree
	 * nodes based on their evaluation cost; the purpose of this function is to
	 * evaluate how to properly order added values in the frontier, which is defined
	 * as a priority queue in the later searchPath function
	 * 
	 * @param n1 and n2 are two tree nodes representing different values in the
	 *           priority queue
	 */
	private static Comparator<SearchTreeNode> compare = (n1, n2) -> {
		return n1.evaluate() - n2.evaluate();
	};

	/**
	 * Given a leaf node in the search tree (a goal), returns a solution by
	 * traversing up the search tree, collecting actions along the way, until
	 * reaching the root
	 * 
	 * @param last SearchTreeNode to start the upward traversal at (a goal node)
	 * @return ArrayList sequence of actions; solution of format ["U", "R", "U",...]
	 */
	private static ArrayList<String> getPath(SearchTreeNode last) {
		ArrayList<String> result = new ArrayList<>();
		for (SearchTreeNode current = last; current.parent != null; current = current.parent) {
			result.add(current.action);
		}
		Collections.reverse(result);
		return result;
	}

	/**
	 * Returns the heuristic cost of a given MazeState from its current position to
	 * the closest goal; this goal does not necessarily mean from the current
	 * position to one of the Goal States, it can also be from the current position
	 * to a Key State, as well. This heuristic function is constructed based on the
	 * Manhattan Distance formula.
	 * 
	 * @param The MazeState of the current position in the maze, the HashSet of
	 *            MazeStates of the goals; again this does not necessarily have to
	 *            be a HashSet of Goal States, it could be a HashSet of Key States
	 * @return integer representation of the history
	 */
	private static int getHeuristic(MazeState current, HashSet<MazeState> goals) {
		int lowestCost = Integer.MAX_VALUE;
		int currentCost = 0;
		for (MazeState goal : goals) {
			currentCost = Math.abs(current.col - goal.col) + Math.abs(current.row - goal.row);
			if (currentCost <= lowestCost) {
				lowestCost = currentCost;
			}
		}
		return currentCost;
	}

	/**
	 * Given a MazeProblem, which specifies the actions and transitions available in
	 * the search, returns a solution to the problem as a sequence of actions that
	 * leads from a defined initial to a goal state. As it was in the case for the
	 * heuristic function, this initial does not need to be the Initial State in the
	 * maze, and the goal does not need to be one of the Goal States. In actuality,
	 * this function is called twice in solve. In the first case, the initial is set
	 * to the Initial State, an the goal, to the Key State. In the second case, the
	 * initial is set to the Key State, and the goal to the nearest Goal State.
	 * 
	 * @param problem A MazeProblem that specifies the maze, actions, transitions, a
	 *                SearchTreeNode, initial, that defines where to start in the
	 *                maze, a HashSet of goalTypes, which defines the HashSet of
	 *                MazeStates for the goal type; this means that what could be
	 *                passed in could be a HashSet of Key States or Goal States.
	 * @return An ArrayList of Strings representing actions that lead from the
	 *         initial to the goal state, of the format: ["R", "R", "L", ...]
	 */
	private static ArrayList<String> searchPath(MazeProblem problem, SearchTreeNode initial,
			HashSet<MazeState> goalType) {
		PriorityQueue<SearchTreeNode> frontier = new PriorityQueue<>(compare);
		Set<MazeState> graveyard = new HashSet<>();
		frontier.add(initial);
		while (!frontier.isEmpty()) {
			SearchTreeNode expanding = frontier.poll();
			graveyard.add(expanding.state);
			if (problem.KEY_STATES == goalType) {
				if (problem.isKey(expanding.state)) {
					return getPath(expanding);
				}
			} else if (problem.GOAL_STATES == goalType) {
				if (problem.isGoal(expanding.state)) {
					return getPath(expanding);
				}
			}
			Map<String, MazeState> transitions = problem.getTransitions(expanding.state);
			for (Map.Entry<String, MazeState> transition : transitions.entrySet()) {
				SearchTreeNode generated = new SearchTreeNode(transition.getValue(), transition.getKey(), expanding,
						getHeuristic(expanding.state, goalType), getHistory(expanding, transition.getValue(), problem));
				if (!graveyard.contains(generated.state)) {
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
	int heuristic;
	int history;

	/**
	 * Constructs a new SearchTreeNode to be used in the Search Tree.
	 * 
	 * @param state     The MazeState (row, col) that this node represents.
	 * @param action    The action that *led to* this state / node.
	 * @param parent    Reference to parent SearchTreeNode in the Search Tree.
	 * @param heuristic int representing cost of the current node.
	 * @param history   int representing cost of the current node.
	 */
	SearchTreeNode(MazeState state, String action, SearchTreeNode parent, int heuristic, int history) {
		this.state = state;
		this.action = action;
		this.parent = parent;
		this.heuristic = heuristic;
		this.history = history;
	}

	/**
	 * Returns the total evaluation cost of a given TreeNode object based on its
	 * given history and heuristic costs.
	 */
	public int evaluate() {
		return history + heuristic;
	}

}