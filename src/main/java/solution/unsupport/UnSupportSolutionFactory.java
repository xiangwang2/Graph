package solution.unsupport;

import solution.RoutesSolution;
import solution.RoutesSolutionFactory;

public class UnSupportSolutionFactory implements RoutesSolutionFactory {
    private String solutionInfo;

    public UnSupportSolutionFactory(String solutionInfo){
        this.solutionInfo = solutionInfo;
    }
    public RoutesSolution getRoutesSolution() {
        return new UnSupportSolution(solutionInfo);
    }
}
