package solution.routesnumber.filter;

import common.RouteDetail;
import solution.routesnumber.Path;
import solution.routesnumber.RoutesNumArg;


/**
 * @version: V1.0
 * @author: XiangWang
 * @className: ExactlyStopFilter
 * @packageName: solution.routesnumber.filter
 * @description: this class is a filter, is used for filter routes which stops not equals the exactly stops.
 **/
public class ExactlyStopFilter implements Filter{
    private int exactlyStops;
    private int backExactlyStops;

    public ExactlyStopFilter(int exactlyStops){
        this.exactlyStops = exactlyStops;
        this.backExactlyStops = exactlyStops;
    }

    public boolean filter(RouteDetail routeDetail, RoutesNumArg arg) {
        return exactlyStops >= 0;
    }

    public void updateFilter(RouteDetail routeDetail) {
        exactlyStops--;
    }

    public boolean filterByPath(Path path) {
        reset();
        int pathPoint = path.getCompletePath().size();
        return pathPoint == exactlyStops;
    }

    private void reset(){
        exactlyStops = backExactlyStops;
    }

    @Override
    public Object clone() {
        return new ExactlyStopFilter(exactlyStops);
    }
}
