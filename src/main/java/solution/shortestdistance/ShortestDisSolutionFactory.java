package solution.shortestdistance;

import common.Constant;
import common.GraphParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solution.RoutesSolution;
import solution.RoutesSolutionFactory;

import java.util.HashSet;
import java.util.Set;

public class ShortestDisSolutionFactory implements RoutesSolutionFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShortestDisSolutionFactory.class);
    private String graphInfo;
    private String solutionInfo;

    public ShortestDisSolutionFactory(String graphInfo, String solutionInfo) {
        this.graphInfo = graphInfo;
        this.solutionInfo = solutionInfo;
    }

    public RoutesSolution getRoutesSolution() {
        GraphParser graphParser = new GraphParser(graphInfo);
        try {
            int cityNum = getCityNumber();
            int[][] adjacencyMatrix = graphParser.parseGraphToMatrix(cityNum);
            return new ShortestDisSolution(adjacencyMatrix, solutionInfo);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    private int getCityNumber() {
        String existedRoute = graphInfo.replace(Constant.GRAPH_FLAG, "");
        Set<Character> citySet = new HashSet<Character>();
        int length = existedRoute.length();
        for (int i = 0; i < length; i++) {
            char temp = existedRoute.charAt(i);
            if (temp >= 'A' && temp <= 'Z'){
                citySet.add(temp);
            }
        }
        return citySet.size();
    }
}
