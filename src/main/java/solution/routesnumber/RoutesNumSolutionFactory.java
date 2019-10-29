package solution.routesnumber;

import common.GraphParser;
import common.RouteDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solution.RoutesSolution;
import solution.RoutesSolutionFactory;

import java.util.List;

public class RoutesNumSolutionFactory implements RoutesSolutionFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoutesNumSolutionFactory.class);
    private String graphInfo;
    private String solutionInfo;

    public RoutesNumSolutionFactory(String graphInfo, String solutionInfo) {
        this.graphInfo = graphInfo;
        this.solutionInfo = solutionInfo;
    }

    public RoutesSolution getRoutesSolution() {
        try {
            GraphParser graphParser = new GraphParser(graphInfo);
            List<RouteDetail> routeDetailList = graphParser.parseGraphToList();
            return new RoutesNumSolution(routeDetailList,solutionInfo);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return null;
        }
    }
}
