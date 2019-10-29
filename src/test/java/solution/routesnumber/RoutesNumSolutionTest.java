package solution.routesnumber;

import common.GraphParser;
import org.junit.BeforeClass;
import org.junit.Test;
import solution.Result;

import static org.junit.Assert.*;

public class RoutesNumSolutionTest {
    private static final String graphInfo = "Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";

    @Test
    public void testRun_Failed() throws Exception {
        GraphParser parser = new GraphParser(graphInfo);
        String solutionInfo = "RoutesNumber: A-C : ExactlyStops-4ASA";
        RoutesNumSolution solution = new RoutesNumSolution(parser.parseGraphToList(), solutionInfo);
        RoutesNumResult result = (RoutesNumResult) solution.run();
        RoutesNumResult expect = new RoutesNumResult(0);
        assertEquals(expect.getRoutesNum(), result.getRoutesNum());
    }

    @Test
    public void testRun_MaxStopsFilter() throws Exception {
        GraphParser parser = new GraphParser(graphInfo);
        String solutionInfo = "RoutesNumber: C-C : MaxStops-3";
        RoutesNumSolution solution = new RoutesNumSolution(parser.parseGraphToList(), solutionInfo);
        RoutesNumResult result = (RoutesNumResult) solution.run();
        RoutesNumResult expect = new RoutesNumResult(2);
        assertEquals(expect.getRoutesNum(), result.getRoutesNum());
    }

    @Test
    public void testRun_ExactlyStopsFilter() throws Exception {
        GraphParser parser = new GraphParser(graphInfo);
        String solutionInfo = "RoutesNumber: A-C : ExactlyStops-4";
        RoutesNumSolution solution = new RoutesNumSolution(parser.parseGraphToList(), solutionInfo);
        RoutesNumResult result = (RoutesNumResult) solution.run();
        RoutesNumResult expect = new RoutesNumResult(3);
        assertEquals(expect.getRoutesNum(), result.getRoutesNum());
    }

    @Test
    public void testRun_DistanceLessFilter() throws Exception {
        GraphParser parser = new GraphParser(graphInfo);
        String solutionInfo = "RoutesNumber: C-C : DistanceLess-30";
        RoutesNumSolution solution = new RoutesNumSolution(parser.parseGraphToList(), solutionInfo);
        RoutesNumResult result = (RoutesNumResult) solution.run();
        RoutesNumResult expect = new RoutesNumResult(7);
        assertEquals(expect.getRoutesNum(), result.getRoutesNum());
    }

    @Test
    public void testRun_ExactlyDuration() throws Exception {
        GraphParser parser = new GraphParser(graphInfo);
        String solutionInfo = "RoutesNumber: A-C : ExactlyDuration-30";
        RoutesNumSolution solution = new RoutesNumSolution(parser.parseGraphToList(), solutionInfo);
        RoutesNumResult result = (RoutesNumResult) solution.run();
        RoutesNumResult expect = new RoutesNumResult(1);
        assertEquals(expect.getRoutesNum(), result.getRoutesNum());
    }
}