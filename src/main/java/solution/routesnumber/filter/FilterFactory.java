package solution.routesnumber.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FilterFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(FilterFactory.class);
    private static final String EXACTLY_STOPS_FLAG = "ExactlyStops";
    private static final String MAX_STOPS_FLAG = "MaxStops";
    private static final String DISTANCE_LESS_FLAG = "DistanceLess";
    private static final String EXACTLY_DURATION_FLAG = "ExactlyDuration";

    public static Filter generateFilter(String filterConfig){
        if (filterConfig.startsWith(EXACTLY_STOPS_FLAG)){
            int exactlyStops = Integer.parseInt(filterConfig.replace(EXACTLY_STOPS_FLAG + "-","").trim());
            return new ExactlyStopFilter(exactlyStops);
        }else if(filterConfig.startsWith(MAX_STOPS_FLAG)){
            int maxStops = Integer.parseInt(filterConfig.replace(MAX_STOPS_FLAG + "-", "").trim());
            return new MaxStopsFilter(maxStops);
        }else if (filterConfig.startsWith(DISTANCE_LESS_FLAG)){
            int maxDistance = Integer.parseInt(filterConfig.replace(DISTANCE_LESS_FLAG + "-", "").trim());
            return new DistanceLessFilter(maxDistance);
        }else if (filterConfig.startsWith(EXACTLY_DURATION_FLAG)){
            int exactlyDuration = Integer.parseInt(filterConfig.replace( EXACTLY_DURATION_FLAG + "-", ""));
            return new ExactlyDurationFilter(exactlyDuration);
        } else {
            LOGGER.error("unsupport this filter: " + filterConfig);
            return null;
        }
    }
}
