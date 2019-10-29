package solution.routesnumber.filter;

import common.RouteDetail;
import solution.routesnumber.Path;
import solution.routesnumber.RoutesNumArg;

public interface Filter extends Cloneable{
    boolean filter(RouteDetail routeDetail, RoutesNumArg arg);

    void updateFilter(RouteDetail routeDetail);

    boolean filterByPath(Path path);

    Object clone();
}

