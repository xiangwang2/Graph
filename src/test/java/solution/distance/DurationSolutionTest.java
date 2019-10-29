package solution.distance;

import common.GraphParser;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DurationSolutionTest {

    private static GraphParser parser;

    @BeforeClass
    public static void beforeClass(){
        String graphInfo = "Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        parser = new GraphParser(graphInfo);
    }

    @Test
    public void testRun_Success() throws Exception {
        String solutionInfo = "Duration:A-B-C";
        DurationSolution solution = new DurationSolution(parser.parseGraphToMap(), solutionInfo);
        DistanceResult result = (DistanceResult) solution.run();
        DistanceResult except = new DistanceResult(11);
        assertEquals(except.getDistance(), result.getDistance());
    }

    @Test
    public void testRun_Success_AEBCD() throws Exception {
        String solutionInfo = "Duration:A-E-B-C-D";
        DurationSolution solution = new DurationSolution(parser.parseGraphToMap(), solutionInfo);
        DistanceResult result = (DistanceResult) solution.run();
        DistanceResult except = new DistanceResult(28);
        assertEquals(except.getDistance(), result.getDistance());
    }
}
