package gateway.route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ztz
 * @description TODO
 * @date 2020/11/3 22:27
 */
public class RouteStrategyFactory {
    private static final Map<StrategyEnum, RouteStrategy> strategies = new HashMap<>();

    static {
        strategies.put(StrategyEnum.RANDOM, new RandomRoute(null));
    }

    public RouteStrategy getStrategy(StrategyEnum strategy) {
        return strategies.get(strategy);
    }

}
