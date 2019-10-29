package common;

public class RouteDetail {
    private String startPoint;
    private String endPoint;
    private int distance;

    public RouteDetail() {
    }

    public RouteDetail(String startPoint, String endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public RouteDetail(String startPoint, String endPoint, int distance) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.distance = distance;
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

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null){
            return false;
        }
        if(!this.getClass().equals(o.getClass())){
            return false;
        }

        RouteDetail routeDetail = (RouteDetail) o;

        return this.startPoint.equals(routeDetail.startPoint) && this.endPoint.equals(routeDetail.endPoint);
    }

    @Override
    public int hashCode(){
        return startPoint.hashCode() + endPoint.hashCode() * 10;
    }

}
