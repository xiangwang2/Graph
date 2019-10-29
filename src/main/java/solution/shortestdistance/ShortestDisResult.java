package solution.shortestdistance;

import solution.Result;

public class ShortestDisResult implements Result {
    private int shortestDistance;

    public ShortestDisResult(int shortestDistance){
        this.shortestDistance = shortestDistance;
    }

    public int getShortestDistance() {
        return shortestDistance;
    }

    @Override
    public String toString(){
        return String.valueOf(shortestDistance);
    }
}
