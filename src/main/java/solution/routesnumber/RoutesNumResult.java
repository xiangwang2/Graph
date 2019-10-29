package solution.routesnumber;

import solution.Result;

public class RoutesNumResult implements Result {
    private int routesNum;

    public RoutesNumResult(int routesNum){
        this.routesNum = routesNum;
    }

    public int getRoutesNum() {
        return routesNum;
    }

    @Override
    public String toString() {
        return String.valueOf(routesNum);
    }
}
