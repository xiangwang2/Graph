package solution.routesnumber.filter;

import common.RouteDetail;
import solution.routesnumber.Path;
import solution.routesnumber.RoutesNumArg;


/**
 * @version: V1.0
 * @author: XiangWang
 * @className: MaxStopsFilter
 * @packageName: solution.routesnumber.filter
 * @description: this class is a filter, is used for filter routes which stops over the max stops.
 **/
public class MaxStopsFilter implements Filter {
    private int maxStops;
    private int backMaxStops;

    public MaxStopsFilter(int maxStops) {
        this.maxStops = maxStops;
        this.backMaxStops = maxStops;
    }

    public boolean filter(RouteDetail routeDetail, RoutesNumArg arg) {
        return maxStops > 0;
    }

    public void updateFilter(RouteDetail routeDetail) {
        maxStops--;
    }

    public boolean filterByPath(Path path) {
        reset();
        int realPathPoint = path.getCompletePath().size();
        return realPathPoint <= maxStops;
    }

    private void reset() {
        maxStops = backMaxStops;
    }

    @Override
    public Object clone() {
        return new MaxStopsFilter(maxStops);
    }
}
