package common;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @version: V1.0
 * @author: XiangWang
 * @className: GraphParser
 * @packageName: common
 * @description: this class is used for parse graph info to Map、list or matrix.
 **/
public class GraphParser {
    private static final String GRAPH_INFO_REGEX = "Graph:(\\s*[a-zA-Z]{2}\\d+,)*(\\s*[a-zA-Z]{2}\\d+)";
    private static final String ROUTE_DETAIL_REGEX = "^([a-zA-Z])([a-zA-Z])(\\d+)";

    private String graphInfo;
    private List<RouteDetail> routeDetailList = new LinkedList<RouteDetail>();
    private Map<RouteDetail, Integer> routeDistance = new HashMap<RouteDetail, Integer>();

    public GraphParser(String graphInfo) {
        this.graphInfo = graphInfo;
    }

    /**
     * @description: parse the graph info to map
     * @return: Map<RouteDetail, Integer> a map stored the routeDetail and distance mapper;
     * @exception: exception if the graph format is invalid;
     * @author:  XiangWang
     */
    public Map<RouteDetail, Integer> parseGraphToMap() throws Exception {
        parse();
        return routeDistance;
    }

    /**
     * @description: parse the graph info to list
     * @return: List<RouteDetail> a list stored existed routeDetail
     * @exception: exception if the graph format is invalid;
     * @author:  XiangWang
     */
    public List<RouteDetail> parseGraphToList() throws Exception {
        parse();
        return routeDetailList;
    }

    /**
     * @description: parse the graph info to adjacency matrix
     * @return: adjacency matrix of citys;
     * @exception: exception if the graph format is invalid;
     * @author:  XiangWang
     */
    public int[][] parseGraphToMatrix(int length) throws Exception {
        if (Pattern.matches(GRAPH_INFO_REGEX, graphInfo)) {
            int[][] matrix = initMatrix(length);
            String routesInfo = graphInfo.replace(Constant.GRAPH_FLAG, "");
            String[] routes = routesInfo.split(",");
            for (String route : routes) {
                RouteDetail routeDetail = getRoute(route);
                char startPoint = routeDetail.getStartPoint().charAt(0);
                char endPoint = routeDetail.getEndPoint().charAt(0);
                int distance = routeDetail.getDistance();
                matrix[startPoint - 'A'][endPoint - 'A'] = distance;
            }
            return matrix;
        } else {
            throw new Exception("Graph info is valid, correct format: Graph: AB4, BC6");
        }
    }

    private void parse() throws Exception {
        if (Pattern.matches(GRAPH_INFO_REGEX, graphInfo)) {
            String routes = graphInfo.replace(Constant.GRAPH_FLAG, "");
            parseRoutes(routes);
        } else {
            throw new Exception("Graph info is valid, correct format: Graph: AB4, BC6");
        }
    }

    private void parseRoutes(String routesInfo) throws Exception {
        String[] routes = routesInfo.split(",");
        for (String route : routes) {
            RouteDetail routeDetail = getRoute(route);
            routeDetailList.add(routeDetail);
            routeDistance.put(routeDetail, routeDetail.getDistance());
        }
    }

    private RouteDetail getRoute(String routeInfo) throws Exception {
        String routeInfoTrim = routeInfo.trim();
        Pattern pattern = Pattern.compile(ROUTE_DETAIL_REGEX);
        Matcher matcher = pattern.matcher(routeInfoTrim);
        if (matcher.find()) {
            String startPoint = String.valueOf(matcher.group(1));
            String endPoint = String.valueOf(matcher.group(2));
            int distance = Integer.parseInt(matcher.group(3));
            return new RouteDetail(startPoint, endPoint, distance);
        } else {
            throw new Exception("Routes format error, correct format: AB4, error tips: " + routeInfo);
        }
    }

    private int[][] initMatrix(int length) {
        int[][] matrix = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i == j) {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = Constant.MAX_DISTANCE;
                }
            }
        }
        return matrix;
    }

}
