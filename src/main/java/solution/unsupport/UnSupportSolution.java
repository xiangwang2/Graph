package solution.unsupport;

import solution.RoutesSolution;

public class UnSupportSolution implements RoutesSolution {
    private String solutionInfo;

    public UnSupportSolution(String solutionInfo) {
        this.solutionInfo = solutionInfo;
    }

    public UnSupportResult run() {
        return new UnSupportResult(solutionInfo);
    }
}
