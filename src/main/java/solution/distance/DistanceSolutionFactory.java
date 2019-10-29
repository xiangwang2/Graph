package solution.distance;

import common.Constant;
import common.GraphParser;
import common.RouteDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solution.RoutesSolution;
import solution.RoutesSolutionFactory;

import java.util.Map;

public class DistanceSolutionFactory implements RoutesSolutionFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(DistanceSolutionFactory.class);

    private String graphInfo;
    private String solutionInfo;

    public DistanceSolutionFactory(String graphInfo, String solutionInfo) {
        this.graphInfo = graphInfo;
        this.solutionInfo = solutionInfo;
    }

    public RoutesSolution getRoutesSolution() {
        try {
            GraphParser graphParser = new GraphParser(graphInfo);
            Map<RouteDetail, Integer> routeDistance = graphParser.parseGraphToMap();
            if (solutionInfo.startsWith(Constant.DISTANCE_SOLUTION_FLAG)){
                return new DistanceSolution(routeDistance, solutionInfo);
            }else {
                return new DurationSolution(routeDistance, solutionInfo);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }


}
