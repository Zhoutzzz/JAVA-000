package gateway.route;

import java.util.List;

/**
 * @author ztz
 * @description TODO
 * @date 2020/11/3 21:58
 */
public class DefaultRouter implements HttpEndpointRouter {

    private final RouteStrategyFactory routeStrategyFactory;

    public DefaultRouter() {
        routeStrategyFactory = new RouteStrategyFactory();
    }

    @Override
    public String route(List<String> endpoints) {
        RouteStrategy strategy = routeStrategyFactory.getStrategy(StrategyEnum.RANDOM);
        return strategy.routeHost(endpoints);

    }
}
