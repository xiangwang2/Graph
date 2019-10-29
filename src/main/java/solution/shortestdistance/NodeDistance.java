package solution.shortestdistance;

public class NodeDistance {
    private final int node;
    private int shortDistance;

    public NodeDistance(int node, int shortDistance){
        this.node = node;
        this.shortDistance = shortDistance;
    }

    public int getNode() {
        return node;
    }

    public int getShortDistance() {
        return shortDistance;
    }

    public void setShortDistance(int shortDistance) {
        this.shortDistance = shortDistance;
    }
}
