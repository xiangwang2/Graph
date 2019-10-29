package solution.shortestdistance;

import common.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solution.Result;
import solution.RoutesSolution;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;


/**
 * @version: V1.0
 * @author: XiangWang
 * @className: ShortestDisSolution
 * @packageName: solution.shortestdistance
 * @description: this class is solution for shortest route, it calculate shortest distance between start and end.
 **/
public class ShortestDisSolution implements RoutesSolution {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShortestDisSolution.class);
    private static final String SHOERTEST_DIS_SOLUTION_INFO_REGEX = "ShortestDistance:\\s*[A-Z]-[A-Z]";

    private int[][] matrix;
    private String solutionInfo;
    private int startPoint;
    private int endPoint;


    public ShortestDisSolution(int[][] matrix, String solutionInfo) {
        this.matrix = matrix.clone();
        this.solutionInfo = solutionInfo;
    }

    public Result run() {
        try {
            parseStartAndEndPoint();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new ShortestDisResult(Constant.MAX_DISTANCE);
        }
        int shortestDistance;
        if (startPoint == endPoint) {
            shortestDistance = getMinCycleDistance();
        } else {
            shortestDistance = dijkstra(startPoint, endPoint);
        }
        return new ShortestDisResult(shortestDistance);
    }


    private void parseStartAndEndPoint() throws Exception{
        if(Pattern.matches(SHOERTEST_DIS_SOLUTION_INFO_REGEX,solutionInfo)) {
            String startAndEnd = solutionInfo.replace(Constant.SHORTEST_DISTANCE_SOLUTION_FLAG, "").trim();
            startPoint = startAndEnd.charAt(0) - 'A';
            endPoint = startAndEnd.charAt(2) - 'A';
        }else {
            throw new Exception("shortest distance solution info format error, correct format: ShortestDistance:A-C");
        }
    }

    private int getMinCycleDistance() {
        List<Integer> distanceList = new LinkedList<Integer>();
        for (int i = 0; i < matrix.length; i++) {
            if (i != startPoint) {
                int realDistance = dijkstra(startPoint, i) + matrix[i][startPoint];
                distanceList.add(realDistance);
            }
        }
        Object[] temp = distanceList.toArray();
        Arrays.sort(temp);
        return (Integer) temp[0];
    }

    private int dijkstra(int startPoint, int endPoint) {
        int pointNum = matrix.length;
        VisitedRecord visitedRecord = new VisitedRecord(pointNum);
        List<NodeDistance> shortestDistanceList = generateNodeDistanceList(pointNum);
        visitedRecord.visit(startPoint);

        int nextPoint = -1;
        int min = Constant.MAX_DISTANCE;
        for (int i = 0; i < pointNum; i++) {
            for (int j = 0; j < pointNum; j++) {
                int currentMin = shortestDistanceList.get(j).getShortDistance();
                if (!visitedRecord.isVisited(j) && currentMin < min) {
                    min = currentMin;
                    nextPoint = j;
                }
            }
            visitedRecord.visit(nextPoint);

            for (int k = 0; k < pointNum; k++) {
                NodeDistance currentNodeDistance = shortestDistanceList.get(k);
                int currentDistance = currentNodeDistance.getShortDistance();
                int distanceAfterNextPoint = shortestDistanceList.get(nextPoint).getShortDistance() + matrix[nextPoint][k];
                if (!visitedRecord.isVisited(k) && currentDistance > distanceAfterNextPoint) {
                    currentNodeDistance.setShortDistance(distanceAfterNextPoint);
                }
            }
        }
        return shortestDistanceList.get(endPoint).getShortDistance();
    }

    private List<NodeDistance> generateNodeDistanceList(int pointNum) {
        List<NodeDistance> result = new LinkedList<NodeDistance>();
        for (int i = 0; i < pointNum; i++) {
            NodeDistance nodeDistance = new NodeDistance(i, matrix[startPoint][i]);
            result.add(nodeDistance);
        }
        return result;
    }

}
