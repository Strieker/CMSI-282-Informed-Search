package pathfinder.informed;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

/**
 * Unit tests for Maze Pathfinder. Tests include completeness and optimality.
 */
public class PathfinderTests {

	@Test
	public void testPathfinder_t0() {
		String[] maze = { "XXXXXXX", "XI...KX", "X.....X", "X.X.XGX", "XXXXXXX" };
		MazeProblem prob = new MazeProblem(maze);
		ArrayList<String> solution = Pathfinder.solve(prob);
		int[] result = prob.testSolution(solution);
		assertEquals(1, result[0]);
		assertEquals(6, result[1]);
	}

	@Test
	public void testPathfinder_t1() {
		String[] maze = { "XXXXXXX", "XI....X", "X.MMM.X", "X.XKXGX", "XXXXXXX" };
		MazeProblem prob = new MazeProblem(maze);
		ArrayList<String> solution = Pathfinder.solve(prob);
		int[] result = prob.testSolution(solution);
		assertEquals(1, result[0]);
		assertEquals(14, result[1]);
	}

	@Test
	public void testPathfinder_t2() {
		String[] maze = { "XXXXXXX", "XI.G..X", "X.MMMGX", "X.XKX.X", "XXXXXXX" };
		MazeProblem prob = new MazeProblem(maze);
		ArrayList<String> solution = Pathfinder.solve(prob);

		int[] result = prob.testSolution(solution);
		assertEquals(1, result[0]);
		assertEquals(10, result[1]);
	}

	@Test
	public void testPathfinder_t3() {
		String[] maze = { "XXXXXXX", "XI.G..X", "X.MXMGX", "X.XKX.X", "XXXXXXX" };
		MazeProblem prob = new MazeProblem(maze);
		ArrayList<String> solution = Pathfinder.solve(prob);
		assertNull(solution);
	}

	@Test
	public void testPathfinder_t4() {
		String[] maze = { "XXXXXXX", "XIXGX.X", "X.MXMGX", "X.XKX.X", "XXXXXXX" };
		MazeProblem prob = new MazeProblem(maze);
		ArrayList<String> solution = Pathfinder.solve(prob);
		assertNull(solution);
	}

	@Test
	public void testPathfinder_t5() {
		String[] maze = { "XXXXXXX", "XIXGX.X", "X.MXMGX", "X..KX.X", "XXXXXXX" };
		MazeProblem prob = new MazeProblem(maze);
		ArrayList<String> solution = Pathfinder.solve(prob);
		assertNull(solution);
	}

	@Test
	public void testPathfinder_t6() {
		String[] maze = { "XXXXXXX", "X..IG.X", "X.M.MXX", "X..KXGX", "XXXXXXX" };
		MazeProblem prob = new MazeProblem(maze);
		ArrayList<String> solution = Pathfinder.solve(prob);

		int[] result = prob.testSolution(solution);
		assertEquals(1, result[0]);
		assertEquals(5, result[1]);
	}

	@Test
	public void testPathfinder_t7() {
		String[] maze = { "XXXXXXX", "X.IXG.X", "X.M.MXX", "X..KXGX", "XXXXXXX" };
		MazeProblem prob = new MazeProblem(maze);
		ArrayList<String> solution = Pathfinder.solve(prob);

		int[] result = prob.testSolution(solution);
		assertEquals(1, result[0]);
		assertEquals(10, result[1]);
	}

	@Test
	public void testPathfinder_t8() {
		String[] maze = { "XXXXXXX", "XIXGX.X", "X.MXMGX", "X.XXX.X", "XXXXXXX" };
		MazeProblem prob = new MazeProblem(maze);
		ArrayList<String> solution = Pathfinder.solve(prob);
		assertNull(solution);
	}

	@Test
	public void testPathfinder_t9() {
		String[] maze = { "XXXXXXX", "XIXXX.X", "X.MXMXX", "X.XKX.X", "XXXXXXX" };
		MazeProblem prob = new MazeProblem(maze);
		ArrayList<String> solution = Pathfinder.solve(prob);
		assertNull(solution);
	}

	@Test
	public void testPathfinder_t10() {
		String[] maze = { "XXXXXXX", "XI....X", "X.....X", "X..K..X", "XXXXXXX" };
		MazeProblem prob = new MazeProblem(maze);
		ArrayList<String> solution = Pathfinder.solve(prob);
		assertNull(solution);
	}

	@Test
	public void testPathfinder_t11() {
		String[] maze = { "XXXXXXX", "XI..G.X", "X.....X", "X..K..X", "XXXXXXX" };
		MazeProblem prob = new MazeProblem(maze);
		ArrayList<String> solution = Pathfinder.solve(prob);
		int[] result = prob.testSolution(solution);
		assertEquals(1, result[0]);
		assertEquals(7, result[1]);
	}

	@Test
	public void testPathfinder_t12() {
		String[] maze = { "XXXXXXX", "XI....X", "X.....X", "X.....X", "XXXXXXX" };
		MazeProblem prob = new MazeProblem(maze);
		ArrayList<String> solution = Pathfinder.solve(prob);
		assertNull(solution);
	}

	@Test
	public void testPathfinder_t13() {
		String[] maze = { "XXXXXXX", "XIXXXXX", "XXXXXXX", "XXXXXXX", "XXXXXXX" };
		MazeProblem prob = new MazeProblem(maze);
		ArrayList<String> solution = Pathfinder.solve(prob);
		assertNull(solution);
	}

	@Test
	public void testPathfinder_t14() {
		String[] maze = { "IXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
				"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
				"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
				"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
				"..........................KG...................................",
				"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", };
		MazeProblem prob = new MazeProblem(maze);
		ArrayList<String> solution = Pathfinder.solve(prob);
		assertNull(solution);
	}

	@Test
	public void testPathfinder_t15() {
		String[] maze = { "XXXXXXX", "XXXXXXX", "XXIKGXX", "XXXXXXX", "XXXXXXX" };
		MazeProblem prob = new MazeProblem(maze);
		ArrayList<String> solution = Pathfinder.solve(prob);
		int[] result = prob.testSolution(solution);
		assertEquals(1, result[0]);
		assertEquals(2, result[1]);
	}

	@Test
	public void testPathfinder_t16() {
		String[] maze = { "XXXXXXX", "X.....X", "X..I..X", "X.....X", "XXXXXXX" };
		MazeProblem prob = new MazeProblem(maze);
		ArrayList<String> solution = Pathfinder.solve(prob);
		assertNull(solution);
	}

	@Test
	public void testPathfinder_t17() {
		String[] maze = { "XXXXXXXXXXXXXXXX", "XIGGGGGGGGGGGGGX", "XGGGGGGGGGGGGGGX", "XGGGGGGGGGGGGGGX",
				"XGGGGGGGGGGGGGGX", "XGGGGGGGGGGGGGGX", "XGGGGGGGGGGGGGGX", "XGGGGGGKGGGGGGGX", "XGGGGGGGGGGGGGGX",
				"XGGGGGGGGGGGGGGX", "XGGGGGGGGGGGGGGX", "XGGGGGGGGGGGGGGX", "XGGGGGGGGGGGGGGX", "XGGGGGGGGGGGGGGX",
				"XGGGGGGGGGGGGGGX", "XGGGGGGGGGGGGGGX", "XGGGGGGGGGGGGGGX", "XGGGGGGGGGGGGGGX", "XGGGGGGGGGGGGGGX",
				"XXXXXXXXXXXXXXXX" };
		MazeProblem prob = new MazeProblem(maze);
		ArrayList<String> solution = Pathfinder.solve(prob);

		int[] result = prob.testSolution(solution);
		assertEquals(1, result[0]);
		assertEquals(13, result[1]);
	}

	@Test
	public void testPathfinder_t18() {
		String[] maze = { "XXXXXXXXXXXXXXXX", "XIGMGMGMGMGMGMGGX", "XMGMGMGMGMGMGMGGX", "XMGMGMGMGMGMGMGX",
				"XMGMGMGMGMGMGMGX", "XMGMGMGMGMGMGMGX", "XGGGMGGXGXGGMGGX", "XGGGMGGKX..MGGGX", "XGGGGGGXGXGGGGGX",
				"XMGMGMGMGMGMGMGX", "XMGMGMGMGMGMGMGX", "XMGMGMGMGMGMGMGX", "XMGMGMGMGMGMGMGX", "XMGMGMGMGMGMGMGX",
				"XMGMGMGMGMGMGMGX", "XGGGGGGGGGGGGGGX", "XGGGGGGGGGGGGGGX", "XGGGGGGGGGGGGGGX", "XGGGGGGGGGGGGGGX",
				"XXXXXXXXXXXXXXXX" };
		MazeProblem prob = new MazeProblem(maze);
		ArrayList<String> solution = Pathfinder.solve(prob);

		int[] result = prob.testSolution(solution);
		assertEquals(1, result[0]);
		assertEquals(15, result[1]);
	}

	@Test
	public void testPathfinder_t19() {
		String[] maze = { "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
				"XIMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMX", "XMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMX",
				"XMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMX", "XMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMX",
				"XMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMX", "XMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMX",
				"XGGGMGGKX..MGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGX", "XMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM.X",
				"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" };
		MazeProblem prob = new MazeProblem(maze);
		ArrayList<String> solution = Pathfinder.solve(prob);

		int[] result = prob.testSolution(solution);
		assertEquals(1, result[0]);
		assertEquals(25, result[1]);
	}

}