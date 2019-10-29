package solution.unsupport;

import solution.Result;

public class UnSupportResult implements Result {
    private String solutionInfo;

    public UnSupportResult(String solutionInfo){
        this.solutionInfo = solutionInfo;
    }

    @Override
    public String toString(){
        return "UnSupport Solution: " + solutionInfo;
    }
}
