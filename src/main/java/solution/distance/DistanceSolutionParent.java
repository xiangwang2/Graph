package solution.distance;

import common.Constant;
import common.RouteDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solution.Result;
import solution.RoutesSolution;

import java.util.LinkedList;
import java.util.List;

public abstract class DistanceSolutionParent implements RoutesSolution {

    @Override
    public abstract Result run();

    public List<RouteDetail> parseSolutionInfo(String realRoutes){
        List<RouteDetail> neededPassRoute = new LinkedList<RouteDetail>();
            int length = realRoutes.length();
            for (int i = 0; i + 2 < length; i += 2) {
                String startPoint = String.valueOf(realRoutes.charAt(i));
                String endPoint = String.valueOf(realRoutes.charAt(i + 2));
                RouteDetail routeDetail = new RouteDetail(startPoint, endPoint);
                neededPassRoute.add(routeDetail);
            }
            return neededPassRoute;
    }
}
