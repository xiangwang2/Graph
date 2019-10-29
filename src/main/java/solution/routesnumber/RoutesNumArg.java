package solution.routesnumber;

import solution.routesnumber.filter.Filter;

import java.util.List;

public class RoutesNumArg {
    private String startPoint;
    private String endPoint;
    private final List<Filter> filterList;

    public RoutesNumArg(String startPoint, String endPoint, List<Filter> filterList) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.filterList = filterList;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public List<Filter> getFilterList() {
        return filterList;
    }
}
