package common;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class GraphParserTest {
    private static String graphInfo;
    private static GraphParser parser;

    @BeforeClass
    public static void beforeClass() {
        graphInfo = "Graph: AB5, BC4, CD8";
        parser = new GraphParser(graphInfo);
    }

    @Test
    public void testParseGraphToMap() throws Exception {
        RouteDetail route1 = new RouteDetail("A", "B", 5);
        RouteDetail route2 = new RouteDetail("B", "C", 4);
        RouteDetail route3 = new RouteDetail("C", "D", 8);
        Map<RouteDetail, Integer> result = parser.parseGraphToMap();
        assertEquals(route1.getDistance(), (int) result.get(route1));
        assertEquals(route2.getDistance(), (int) result.get(route2));
        assertEquals(route3.getDistance(), (int) result.get(route3));
    }

    @Test
    public void testParseGraphToList() throws Exception {
        RouteDetail route1 = new RouteDetail("A", "B", 5);
        RouteDetail route2 = new RouteDetail("B", "C", 4);
        RouteDetail route3 = new RouteDetail("C", "D", 8);
        List<RouteDetail> result = parser.parseGraphToList();
        assertTrue(result.contains(route1));
        assertTrue(result.contains(route2));
        assertTrue(result.contains(route3));
    }

    @Test
    public void testParseGraphToMatrix() throws Exception {
        int[][] expect = {{0, 5, Constant.MAX_DISTANCE, Constant.MAX_DISTANCE},
                {Constant.MAX_DISTANCE, 0, 4, Constant.MAX_DISTANCE},
                {Constant.MAX_DISTANCE, Constant.MAX_DISTANCE, 0, 8},
                {Constant.MAX_DISTANCE, Constant.MAX_DISTANCE, Constant.MAX_DISTANCE, 0}};

        int[][] result = parser.parseGraphToMatrix(4);
        assertArrayEquals(expect[0], result[0]);
        assertArrayEquals(expect[1], result[1]);
        assertArrayEquals(expect[2], result[2]);
        assertArrayEquals(expect[3], result[3]);
    }
}
