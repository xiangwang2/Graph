package solution.distance;

import solution.Result;

public class DistanceResult implements Result {
    private static final String NO_SUCH_ROUTE = "NO SUCH ROUTE";
    private int distance;

    public DistanceResult(int distance){
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public String toString(){
        if (distance > 0){
            return String.valueOf(distance);
        }else {
            return NO_SUCH_ROUTE;
        }
    }
}
