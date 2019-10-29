package routesmain;

import common.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solution.Result;
import solution.RoutesSolution;
import solution.RoutesSolutionFactory;
import solution.distance.DistanceSolutionFactory;
import solution.routesnumber.RoutesNumSolutionFactory;
import solution.shortestdistance.ShortestDisSolutionFactory;
import solution.unsupport.UnSupportSolution;
import solution.unsupport.UnSupportSolutionFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * @version: V1.0
 * @author: XiangWang
 * @className: RoutesInfo
 * @packageName: routesmain
 * @description: this class is used for parse input and generate routes solution,then run the solution and show result.
 **/
public class RoutesInfo {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoutesInfo.class);

    private String graphInfo;
    private List<String> solutionInfoList;
    private List<RoutesSolution> solutionList = new LinkedList<RoutesSolution>();
    private List<Result> resultList = new LinkedList<Result>();

    public RoutesInfo(String graphInfo, List<String> solutionInfoList) {
        this.graphInfo = graphInfo;
        this.solutionInfoList = solutionInfoList;
    }

    public void show() {
        generateSolution();
        getSolutionsResult();
        showResult();
    }

    private void generateSolution() {
        for (String solutionInfo : solutionInfoList) {
            RoutesSolutionFactory factory = getRoutesSolutionFactory(solutionInfo);
            RoutesSolution solution = factory.getRoutesSolution();
            if (solution != null) {
                solutionList.add(factory.getRoutesSolution());
            }else {
                solutionList.add(new UnSupportSolution(solutionInfo));
            }
        }
    }

    private RoutesSolutionFactory getRoutesSolutionFactory(String solutionInfo) {
        if (solutionInfo.startsWith(Constant.DISTANCE_SOLUTION_FLAG) || solutionInfo.startsWith(Constant.DURATION_SOLUTION_FLAG)) {
            return new DistanceSolutionFactory(graphInfo, solutionInfo);
        } else if (solutionInfo.startsWith(Constant.ROUTES_NUMBER_SOLUTION_FLAG)) {
            return new RoutesNumSolutionFactory(graphInfo, solutionInfo);
        } else if (solutionInfo.startsWith(Constant.SHORTEST_DISTANCE_SOLUTION_FLAG)) {
            return new ShortestDisSolutionFactory(graphInfo, solutionInfo);
        } else {
            return new UnSupportSolutionFactory(solutionInfo);
        }
    }

    private void getSolutionsResult() {
        for (RoutesSolution routesSolution : solutionList) {
            resultList.add(routesSolution.run());
        }
    }

    private void showResult() {
        for (Result result : resultList) {
            LOGGER.info(result.toString());
        }
    }
}
