package solution.shortestdistance;

public class VisitedRecord {
    private int[] visitedFlag;

    public VisitedRecord(int pointNum){
        visitedFlag = new int[pointNum];
    }

    public void visit(int point){
        visitedFlag[point] = 1;
    }

    public boolean isVisited(int point){
        return visitedFlag[point] == 1;
    }
}
