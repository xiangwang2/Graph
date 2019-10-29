package solution.shortestdistance;

import common.Constant;
import common.GraphParser;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShortestDisSolutionTest {
    private static GraphParser parser;

    @BeforeClass
    public static void beforeClass(){
        String graphInfo = "Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        parser = new GraphParser(graphInfo);
    }

    @Test
    public void testRun_Failed() throws Exception {
        String solutionInfo = "ShortestDistance:A-C-D";
        ShortestDisSolution solution = new ShortestDisSolution(parser.parseGraphToMatrix(5),solutionInfo);
        ShortestDisResult result = (ShortestDisResult) solution.run();
        ShortestDisResult except = new ShortestDisResult(Constant.MAX_DISTANCE);
        assertEquals(except.getShortestDistance(), result.getShortestDistance());
    }

    @Test
    public void testRun_StartEqualsEnd() throws Exception {
        String solutionInfo = "ShortestDistance:B-B";
        ShortestDisSolution solution = new ShortestDisSolution(parser.parseGraphToMatrix(5),solutionInfo);
        ShortestDisResult result = (ShortestDisResult) solution.run();
        ShortestDisResult except = new ShortestDisResult(9);
        assertEquals(except.getShortestDistance(), result.getShortestDistance());
    }

    @Test
    public void testRun_StartNotEqualsEnd() throws Exception {
        String solutionInfo = "ShortestDistance:A-C";
        ShortestDisSolution solution = new ShortestDisSolution(parser.parseGraphToMatrix(5),solutionInfo);
        ShortestDisResult result = (ShortestDisResult) solution.run();
        ShortestDisResult except = new ShortestDisResult(9);
        assertEquals(except.getShortestDistance(), result.getShortestDistance());
    }
}