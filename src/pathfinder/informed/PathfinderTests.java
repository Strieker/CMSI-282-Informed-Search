package pathfinder.informed;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

/**
 * Unit tests for Maze Pathfinder. Tests include completeness and
 * optimality.
 */
public class PathfinderTests {
    
    @Test
    public void testPathfinder_t0() {
        String[] maze = {
            "XXXXXXX",
            "XI...KX",
            "X.....X",
            "X.X.XGX",
            "XXXXXXX"
        };
//        System.out.println(new MazeProblem(maze));
        MazeProblem prob = new MazeProblem(maze);
        ArrayList<String> solution = Pathfinder.solve(prob);
        
        // result will be a 2-tuple (isSolution, cost) where
        // - isSolution = 0 if it is not, 1 if it is
        // - cost = numerical cost of proposed solution
        int[] result = prob.testSolution(solution);
        System.out.println(solution.toString());

        assertEquals(1, result[0]); // Test that result is a solution
        assertEquals(6, result[1]); // Ensure that the solution is optimal
    }
    
    // NOT FINDING MOST OPTIMAL SOLUTION FOR THE GOAL STATE 
    @Test
    public void testPathfinder_t1() {
        String[] maze = {
            "XXXXXXX",
            "XI....X",
            "X.MMM.X",
            "X.XKXGX",
            "XXXXXXX"
        };
        MazeProblem prob = new MazeProblem(maze);
        ArrayList<String> solution = Pathfinder.solve(prob);
        int[] result = prob.testSolution(solution);
        System.out.println(solution.toString());

        assertEquals(1, result[0]);  // Test that result is a solution
        assertEquals(14, result[1]); // Ensure that the solution is optimal
    }
    // HOW TO HANDLE MULTIPLE GOAL STATES 
    @Test
    public void testPathfinder_t2() {
        String[] maze = {
            "XXXXXXX",
            "XI.G..X",
            "X.MMMGX",
            "X.XKX.X",
            "XXXXXXX"
        };
        MazeProblem prob = new MazeProblem(maze);
        ArrayList<String> solution = Pathfinder.solve(prob);
        
        int[] result = prob.testSolution(solution);
        System.out.println(solution.toString());
        assertEquals(1, result[0]);  // Test that result is a solution
        assertEquals(10, result[1]); // Ensure that the solution is optimal
    }
    
    @Test
    public void testPathfinder_t3() {
        String[] maze = {
            "XXXXXXX",
            "XI.G..X",
            "X.MXMGX",
            "X.XKX.X",
            "XXXXXXX"
        };
        MazeProblem prob = new MazeProblem(maze);
        ArrayList<String> solution = Pathfinder.solve(prob);
        assertNull(solution); // Ensure that Pathfinder knows when there's no solution
    }
    
    // TODO no key 
    // TODO goal or key is unreachable 
    @Test
    public void testPathfinder_t5() {
        String[] maze = {
            "XXXXXXX",
            "XIXGX.X",
            "X.MXMGX",
            "X.XKX.X",
            "XXXXXXX"
        };
        MazeProblem prob = new MazeProblem(maze);
        ArrayList<String> solution = Pathfinder.solve(prob);
        assertNull(solution); // Ensure that Pathfinder knows when there's no solution
    }
    
    @Test
    public void testPathfinder_t4() {
        String[] maze = {
            "XXXXXXX",
            "XIXGX.X",
            "X.MXMGX",
            "X..KX.X",
            "XXXXXXX"
          };
            MazeProblem prob = new MazeProblem(maze);
            ArrayList<String> solution = Pathfinder.solve(prob);
            assertNull(solution); // Ensure that Pathfinder knows when there's no solution
    }

    @Test
    public void testPathfinder_t6() {
        String[] maze = {
            "XXXXXXX",
            "X..IG.X",
            "X.M.MXX",
            "X..KXGX",
            "XXXXXXX"
          };
      MazeProblem prob = new MazeProblem(maze);
      ArrayList<String> solution = Pathfinder.solve(prob);
  
      int[] result = prob.testSolution(solution);
      System.out.println(solution.toString());
      assertEquals(1, result[0]);  // Test that result is a solution
      assertEquals(5, result[1]); // Ensure that the solution is optimal
    }
    

    @Test
    public void testPathfinder_t7() {
        String[] maze = {
            "XXXXXXX",
            "X.IXG.X",
            "X.M.MXX",
            "X..KXGX",
            "XXXXXXX"
          };
      MazeProblem prob = new MazeProblem(maze);
      ArrayList<String> solution = Pathfinder.solve(prob);
  
      int[] result = prob.testSolution(solution);
      System.out.println(solution.toString());
      assertEquals(1, result[0]);  // Test that result is a solution
      assertEquals(10, result[1]); // Ensure that the solution is optimal
    }
    
    // TODO NO KEY 
    @Test
    public void testPathfinder_t8() {
        String[] maze = {
            "XXXXXXX",
            "XIXGX.X",
            "X.MXMGX",
            "X.XXX.X",
            "XXXXXXX"
        };
        MazeProblem prob = new MazeProblem(maze);
        ArrayList<String> solution = Pathfinder.solve(prob);
        assertNull(solution); // Ensure that Pathfinder knows when there's no solution
    }
    
    // TODO NO GOAL 
    @Test
    public void testPathfinder_t9() {
        String[] maze = {
            "XXXXXXX",
            "XIXXX.X",
            "X.MXMXX",
            "X.XKX.X",
            "XXXXXXX"
        };
        MazeProblem prob = new MazeProblem(maze);
        ArrayList<String> solution = Pathfinder.solve(prob);
        assertNull(solution); // Ensure that Pathfinder knows when there's no solution
    }
    
    
}