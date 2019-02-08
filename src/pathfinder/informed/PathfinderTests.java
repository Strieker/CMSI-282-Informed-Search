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
        MazeProblem prob = new MazeProblem(maze);
        ArrayList<String> solution = Pathfinder.solve(prob);
        int[] result = prob.testSolution(solution);
        System.out.println(solution.toString());

        assertEquals(1, result[0]);
        assertEquals(6, result[1]);
    }
    
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

        assertEquals(1, result[0]);
        assertEquals(14, result[1]);
    }

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
        assertEquals(1, result[0]); 
        assertEquals(10, result[1]);
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
        assertNull(solution); 
    }
    
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
        assertNull(solution);
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
            assertNull(solution);
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
      assertEquals(1, result[0]);
      assertEquals(5, result[1]); 
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
      assertEquals(1, result[0]);
      assertEquals(10, result[1]); 
    }
    
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
        assertNull(solution); 
    }
    
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
        assertNull(solution);
    }
    
    @Test
    public void testPathfinder_t10() {
        String[] maze = {
            ".......",
            ".I.....",
            ".......",
            "...K...",
            "......."
        };
        MazeProblem prob = new MazeProblem(maze);
        ArrayList<String> solution = Pathfinder.solve(prob);
        assertNull(solution); 
    }
    
    @Test
    public void testPathfinder_t11() {
        String[] maze = {
            ".......",
            ".I..G..",
            ".......",
            "...K...",
            "......."
        };
        MazeProblem prob = new MazeProblem(maze);
        ArrayList<String> solution = Pathfinder.solve(prob);
        int[] result = prob.testSolution(solution);
        System.out.println(solution.toString());
        assertEquals(1, result[0]); 
        assertEquals(7, result[1]); 
    }
    
    @Test
    public void testPathfinder_t12() {
        String[] maze = {
            ".......",
            ".I.....",
            ".......",
            ".......",
            "......."
        };
        MazeProblem prob = new MazeProblem(maze);
        ArrayList<String> solution = Pathfinder.solve(prob);
        assertNull(solution); 
    }
    
    @Test
    public void testPathfinder_t13() {
        String[] maze = {
            "XXXXXXX",
            "XIXXXXX",
            "XXXXXXX",
            "XXXXXXX",
            "XXXXXXX"
        };
        MazeProblem prob = new MazeProblem(maze);
        ArrayList<String> solution = Pathfinder.solve(prob);
        assertNull(solution); 
    }
    
    @Test
    public void testPathfinder_t14() {
        String[] maze = {
            "XXXXXXX",
            "XXXXXXX",
            "XXXXXXX",
            "XXXXXXX",
            "XXXXXXX"
        };
        MazeProblem prob = new MazeProblem(maze);
        ArrayList<String> solution = Pathfinder.solve(prob);
        assertNull(solution); 
    }
    
    @Test
    public void testPathfinder_t15() {
        String[] maze = {
            "XXXXXXX",
            "XXXXXXX",
            "XXXKGXX",
            "XXXXXXX",
            "XXXXXXX"
        };
        MazeProblem prob = new MazeProblem(maze);
        ArrayList<String> solution = Pathfinder.solve(prob);
        assertNull(solution);
    }
    
    @Test
    public void testPathfinder_t16() {
        String[] maze = {
            ".......",
            ".......",
            ".......",
            ".......",
            "......."
        };
        MazeProblem prob = new MazeProblem(maze);
        ArrayList<String> solution = Pathfinder.solve(prob);
        assertNull(solution);
    }
    
    @Test
    public void testPathfinder_t17() {
        String[] maze = {
            "GGGGGGG",
            "GGGGGGG",
            "GGGGGGG",
            "GGGGGGG",
            "GGGGGGG"
        };
        MazeProblem prob = new MazeProblem(maze);
        ArrayList<String> solution = Pathfinder.solve(prob);
        assertNull(solution); 
    }
    
    @Test
    public void testPathfinder_t18() {
        String[] maze = {
            "KKKKKKK",
            "KKKKKKK",
            "KKKKKKK",
            "KKKKKKK",
            "KKKKKKK"
        };
        MazeProblem prob = new MazeProblem(maze);
        ArrayList<String> solution = Pathfinder.solve(prob);
        assertNull(solution); 
    }
    
    @Test
    public void testPathfinder_t19() {
        String[] maze = {
            "IKKKKKK",
            "KKKKKKK",
            "KKKKKKK",
            "KKKKKKK",
            "KKKKKKG"
        };
        MazeProblem prob = new MazeProblem(maze);
        ArrayList<String> solution = Pathfinder.solve(prob);
        assertNull(solution); 
    }
    
    @Test
    public void testPathfinder_t20() {
        String[] maze = {
            "IIIIIII",
            "IIIIIII",
            "IIIIIII",
            "IIIIIII",
            "IIIIIKG"
        };
        MazeProblem prob = new MazeProblem(maze);
        ArrayList<String> solution = Pathfinder.solve(prob);
        assertNull(solution); 
    }
    
    @Test
    public void testPathfinder_t21() {
        String[] maze = {
            "IIIIIII",
            "IIIIIII",
            "IIIIIII",
            "IIIIIII",
            "IIIIIII"
        };
        MazeProblem prob = new MazeProblem(maze);
        ArrayList<String> solution = Pathfinder.solve(prob);
        assertNull(solution); 
    }
    
    @Test
    public void testPathfinder_t22() {
        String[] maze = {
            "IGIGIGI",
            "IGIGIGI",
            "IGIGIGI",
            "IGIGIGI",
            "IGIGIGI"
        };
        MazeProblem prob = new MazeProblem(maze);
        ArrayList<String> solution = Pathfinder.solve(prob);
        assertNull(solution);
    }
    
    @Test
    public void testPathfinder_t23() {
        String[] maze = {
            "IIIIIII",
            "IIIIIII",
            "IIIIIII",
            "IIIIIII",
            "IIIIIIK"
        };
        MazeProblem prob = new MazeProblem(maze);
        ArrayList<String> solution = Pathfinder.solve(prob);
        assertNull(solution); 
    }
    
    @Test
    public void testPathfinder_t24() {
        String[] maze = {
            "IKIKIKI",
            "IKIKIKI",
            "IKIKIKI",
            "IKIKIKI",
            "IKIKIKI"
        };
        MazeProblem prob = new MazeProblem(maze);
        ArrayList<String> solution = Pathfinder.solve(prob);
        assertNull(solution);
    }
    
}