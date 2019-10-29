package routesmain;

import common.ResourceUtil;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class RoutesSolutionApplication {
    private static final String DEFAULT_FILE_CHARSET = "UTF-8";

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            throw new Exception("Input error, please input the input file path");
        }
        String inputFilePath = args[0];
        RoutesInfo routesInfo = getRoutesInfo(inputFilePath);
        routesInfo.show();
    }

    private static RoutesInfo getRoutesInfo(String filePath) throws Exception {
        File inputFile = new File(filePath);
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(inputFile);
            inputStreamReader = new InputStreamReader(fileInputStream, DEFAULT_FILE_CHARSET);
            bufferedReader = new BufferedReader(inputStreamReader);
            String graphInfo = bufferedReader.readLine();
            List<String> solutionInfoList = new LinkedList<String>();
            while (bufferedReader.ready()) {
                String solutionInfo = bufferedReader.readLine();
                if (solutionInfo != "") {
                    solutionInfoList.add(solutionInfo);
                }
            }
            return new RoutesInfo(graphInfo, solutionInfoList);
        } catch (FileNotFoundException e) {
            throw new Exception("input file is not exist, please input the correct path");
        }finally {
            ResourceUtil.closeStream(fileInputStream);
            ResourceUtil.closeStream(inputStreamReader);
            ResourceUtil.closeStream(bufferedReader);
        }
    }
}
