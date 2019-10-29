package solution.routesnumber;

import common.RouteDetail;

import java.util.LinkedList;
import java.util.List;

public class Path implements Cloneable {
    private List<RouteDetail> completePath;

    public Path() {
        completePath = new LinkedList<RouteDetail>();
    }

    public Path(List<RouteDetail> completePath) {
        this.completePath = completePath;
    }

    public List<RouteDetail> getCompletePath() {
        return completePath;
    }

    public boolean addNextRoute(RouteDetail routeDetail) {
        return completePath.add(routeDetail);
    }

    public RouteDetail getLastRoute(){
        return completePath.get(completePath.size() - 1);
    }

    public String getEndPoint() {
        return completePath.get(completePath.size() - 1).getEndPoint();
    }

    @Override
    public Object clone(){
        List<RouteDetail> copy = new LinkedList<RouteDetail>();
        for (RouteDetail routeDetail : completePath){
            copy.add(routeDetail);
        }
        return new Path(copy);
    }

}
