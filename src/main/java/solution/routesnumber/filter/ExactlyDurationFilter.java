package solution.routesnumber.filter;

import common.Constant;
import common.RouteDetail;
import solution.routesnumber.Path;
import solution.routesnumber.RoutesNumArg;

import java.util.List;

public class ExactlyDurationFilter implements Filter{
    private int exactlyDuration;
    private int backExactlyDuration;

    public ExactlyDurationFilter(int exactlyDuration) {
        this.exactlyDuration = exactlyDuration;
        this.backExactlyDuration = exactlyDuration;
    }

    @Override
    public boolean filter(RouteDetail routeDetail, RoutesNumArg arg) {
        return routeDetail.getDistance() < exactlyDuration;
    }

    @Override
    public void updateFilter(RouteDetail routeDetail) {
        exactlyDuration = exactlyDuration -routeDetail.getDistance() - Constant.STOP_DURATION;
    }

    @Override
    public boolean filterByPath(Path path) {
        reset();
        int duration = 0;
        List<RouteDetail> completePath = path.getCompletePath();
        for (RouteDetail routeDetail : completePath){
            duration = duration + routeDetail.getDistance() + Constant.STOP_DURATION;
        }
        duration -= Constant.STOP_DURATION;
        return duration == exactlyDuration;
    }

    private void reset(){
        this.exactlyDuration = backExactlyDuration;
    }

    @Override
    public Object clone() {
        return new ExactlyDurationFilter(exactlyDuration);
    }
}
