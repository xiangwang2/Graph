package solution.distance;

import common.Constant;
import common.RouteDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solution.Result;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class DurationSolution extends DistanceSolutionParent{
    private static final Logger LOGGER = LoggerFactory.getLogger(DistanceSolution.class);
    private static final String DURATION_SOLUTION_INFO_REGEX = "Duration:([a-zA-Z])(-[a-zA-Z])+";

    private Map<RouteDetail, Integer> routeDistance;
    private String solutionInfo;

    public DurationSolution(Map<RouteDetail, Integer> routeDistance, String solutionInfo) {
        this.routeDistance = routeDistance;
        this.solutionInfo = solutionInfo;
    }

    public Result run() {
        try {
            int duration = 0;
            if (Pattern.matches(DURATION_SOLUTION_INFO_REGEX, solutionInfo)) {
                String realRoutes = solutionInfo.replace(Constant.DURATION_SOLUTION_FLAG, "");
                List<RouteDetail> neededPassRoute = super.parseSolutionInfo(realRoutes);
                for (RouteDetail routeDetail : neededPassRoute) {
                    int distance = routeDistance.get(routeDetail);
                    duration += distance;
                    duration += Constant.STOP_DURATION;
                }
                return new DistanceResult(duration - Constant.STOP_DURATION);
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
