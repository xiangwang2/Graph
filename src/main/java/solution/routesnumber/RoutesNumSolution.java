package solution.routesnumber;

import common.Constant;
import common.RouteDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solution.Result;
import solution.RoutesSolution;
import solution.routesnumber.filter.Filter;
import solution.routesnumber.filter.FilterFactory;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;


/**
 * @version: V1.0
 * @author: XiangWang
 * @className: RoutesNumSolution
 * @packageName: solution.routesnumber
 * @description: this class is solution for routes num, it calculate the number of route which satisfies many condition.
 **/
public class RoutesNumSolution implements RoutesSolution {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoutesNumSolution.class);
    private static final String ROUTES_NUM_SOLUTION_INFO_REGEX = "RoutesNumber:\\s*[a-zA-Z]-[a-zA-Z]\\s*:" +
            "\\s*(MaxStops|ExactlyStops|DistanceLess|ExactlyDuration)-\\d+\\s*(,\\s*(MaxStops|ExactlyStops|DistanceLess|ExactlyDuration)-\\d+\\s*)*";

    private List<RouteDetail> routeDetailList;
    private String solutionInfo;
    private List<Path> pathList = new LinkedList<Path>();

    public RoutesNumSolution(List<RouteDetail> routeDetailList, String solutionInfo) {
        this.routeDetailList = routeDetailList;
        this.solutionInfo = solutionInfo;
    }

    public Result run() {
        try {
            RoutesNumArg arg = parseSolutionInfo();
            generateValidPath(arg, null);
            filterPathByEndPoint(arg.getEndPoint());
            filterByPathFilter(arg.getFilterList());
            return new RoutesNumResult(pathList.size());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new RoutesNumResult(0);
        }
    }

    private RoutesNumArg parseSolutionInfo() throws Exception {
        if (Pattern.matches(ROUTES_NUM_SOLUTION_INFO_REGEX, solutionInfo)) {
            String argsInfo = solutionInfo.replace(Constant.ROUTES_NUMBER_SOLUTION_FLAG, "");
            String[] temp = argsInfo.split(":");
            String[] startAndEnd = temp[0].trim().split("-");
            String filterConfig = temp[1].trim();
            String startPoint = startAndEnd[0].trim();
            String endPoint = startAndEnd[1].trim();
            List<Filter> filterList = parseFilterConfig(filterConfig);
            return new RoutesNumArg(startPoint, endPoint, filterList);
        } else {
            throw new Exception("routes num solution info format is error, correct is: RoutesNumber:A-C:ExactlyStops-4");
        }
    }

    private List<Filter> parseFilterConfig(String filterConfig) {
        List<Filter> filterList = new LinkedList<Filter>();
        String[] filterConfigs = filterConfig.split(",");
        for (String singleFilterConfig : filterConfigs) {
            Filter filter = FilterFactory.generateFilter(singleFilterConfig.trim());
            filterList.add(filter);
        }
        return filterList;
    }

    private void generateValidPath(RoutesNumArg arg, Path lastPath) {
        List<RouteDetail> nextRoutes = findRoutesByStartPoint(arg.getStartPoint());
        filterRouteDetail(nextRoutes, arg);
        List<Path> nextRoutePathList = getNextRoutePathList(nextRoutes, lastPath);
        pathList.addAll(nextRoutePathList);

        for (Path nextRoutePath : nextRoutePathList) {
            RouteDetail lastRoute = nextRoutePath.getLastRoute();
            String newStartPoint = lastRoute.getEndPoint();
            String endPoint = arg.getEndPoint();
            List<Filter> filterList = arg.getFilterList();
            List<Filter> newFilterList = new LinkedList<Filter>();
            for (Filter filter : filterList) {
                Filter newFilter = (Filter) filter.clone();
                newFilter.updateFilter(lastRoute);
                newFilterList.add(newFilter);
            }
            RoutesNumArg newArg = new RoutesNumArg(newStartPoint, endPoint, newFilterList);
            generateValidPath(newArg, nextRoutePath);
        }
    }

    private List<RouteDetail> findRoutesByStartPoint(String startPoint) {
        List<RouteDetail> routes = new LinkedList<RouteDetail>();
        for (RouteDetail routeDetail : routeDetailList) {
            if (routeDetail.getStartPoint().equals(startPoint)) {
                routes.add(routeDetail);
            }
        }
        return routes;
    }

    private void filterRouteDetail(List<RouteDetail> routeDetailList, RoutesNumArg arg) {
        Iterator<RouteDetail> iterator = routeDetailList.iterator();
        List<Filter> filterList = arg.getFilterList();
        for (; iterator.hasNext(); ) {
            RouteDetail routeDetail = iterator.next();
            for (Filter filter : filterList) {
                boolean isValid = filter.filter(routeDetail, arg);
                if (!isValid) {
                    iterator.remove();
                }
            }
        }
    }

    private List<Path> getNextRoutePathList(List<RouteDetail> nextRoutes, Path lastPath) {
        List<Path> nextRoutePathList = new LinkedList<Path>();
        for (RouteDetail nextRoute : nextRoutes) {
            Path newPath;
            if (lastPath != null) {
                newPath = (Path) lastPath.clone();
            } else {
                newPath = new Path();
            }
            newPath.addNextRoute(nextRoute);
            nextRoutePathList.add(newPath);
        }
        return nextRoutePathList;
    }

    private void filterPathByEndPoint(String endPoint) {
        Iterator<Path> iterator = pathList.iterator();
        while (iterator.hasNext()) {
            Path path = iterator.next();
            if (!path.getEndPoint().equals(endPoint)) {
                iterator.remove();
            }
        }
    }

    private void filterByPathFilter(List<Filter> filterList) {
        Iterator<Path> iterator = pathList.iterator();
        while (iterator.hasNext()) {
            Path path = iterator.next();
            boolean isValid = true;
            for (Filter filter : filterList) {
                if (!filter.filterByPath(path)) {
                    isValid = false;
                }
            }
            if (!isValid) {
                iterator.remove();
            }
        }
    }
}
