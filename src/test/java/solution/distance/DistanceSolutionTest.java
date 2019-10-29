package solution.distance;

import common.GraphParser;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class DistanceSolutionTest {
    private static GraphParser parser;

    @BeforeClass
    public static void beforeClass(){
        String graphInfo = "Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        parser = new GraphParser(graphInfo);
    }

    @Test
    public void testRun_Success() throws Exception {
        String solutionInfo = "Distance:A-B-C";
        DistanceSolution solution = new DistanceSolution(parser.parseGraphToMap(), solutionInfo);
        DistanceResult result = (DistanceResult) solution.run();
        DistanceResult except = new DistanceResult(9);
        assertEquals(except.getDistance(), result.getDistance());
    }

    @Test
    public void testRun_Failed() throws Exception {
        String solutionInfo = "Distance:A-E-D";
        DistanceSolution solution = new DistanceSolution(parser.parseGraphToMap(), solutionInfo);
        DistanceResult result = (DistanceResult) solution.run();
        DistanceResult except = new DistanceResult(0);
        assertEquals(except.getDistance(), result.getDistance());
    }
}