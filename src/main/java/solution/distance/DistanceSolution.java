package solution.distance;

import common.Constant;
import common.RouteDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solution.Result;
import solution.RoutesSolution;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


/**
 * @version: V1.0
 * @author: XiangWang
 * @className: DistanceSolution
 * @packageName: solution.distance
 * @description: this class is solution for distance, it calculate distance exactly between the input routes.
 **/
public class DistanceSolution extends DistanceSolutionParent{
    private static final Logger LOGGER = LoggerFactory.getLogger(DistanceSolution.class);
    public static final String DISTANCE_SOLUTION_INFO_REGEX = "Distance:([a-zA-Z])(-[a-zA-Z])+";

    private Map<RouteDetail, Integer> routeDistance;
    private String solutionInfo;

    public DistanceSolution(Map<RouteDetail, Integer> routeDistance, String solutionInfo) {
        this.routeDistance = routeDistance;
        this.solutionInfo = solutionInfo;
    }

    public Result run() {
        try {
            int totalDistance = 0;
            if (Pattern.matches(DISTANCE_SOLUTION_INFO_REGEX, solutionInfo)) {
                String realRoutes = solutionInfo.replace(Constant.DISTANCE_SOLUTION_FLAG, "");
                List<RouteDetail> neededPassRoute = super.parseSolutionInfo(realRoutes);
                for (RouteDetail routeDetail : neededPassRoute) {
                    int distance = routeDistance.get(routeDetail);
                    totalDistance += distance;
                }
                return new DistanceResult(totalDistance);
            }else {
                throw new Exception("parse distance info failed, correct format: Distance: A-B-C");
            }
        } catch (NullPointerException e) {
            return new DistanceResult(0);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new DistanceResult(0);
        }
    }
}
