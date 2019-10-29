package solution.routesnumber.filter;

import common.RouteDetail;
import solution.routesnumber.Path;
import solution.routesnumber.RoutesNumArg;

import java.util.List;


/**
 * @version: V1.0
 * @author: XiangWang
 * @className: DistanceLessFilter
 * @packageName: solution.routesnumber.filter
 * @description: this class is a filter, is used for filter routes which distance over the max distance.
 **/
public class DistanceLessFilter implements Filter{
    private int maxDistance;
    private int backMaxDistance;

    public DistanceLessFilter(int maxDistance){
        this.maxDistance = maxDistance;
        this.backMaxDistance = maxDistance;
    }

    public boolean filter(RouteDetail routeDetail, RoutesNumArg arg) {
        return routeDetail.getDistance() < maxDistance;
    }

    public void updateFilter(RouteDetail routeDetail) {
        maxDistance -= routeDetail.getDistance();
    }

    public boolean filterByPath(Path path) {
        reset();
        int totalDistance = 0;
        List<RouteDetail> routeDetailList = path.getCompletePath();
        for (RouteDetail routeDetail : routeDetailList){
            totalDistance += routeDetail.getDistance();
        }
        return totalDistance <= maxDistance;
    }

    private void reset(){
        this.maxDistance = backMaxDistance;
    }

    @Override
    public Object clone(){
        return new DistanceLessFilter(maxDistance);
    }
}
