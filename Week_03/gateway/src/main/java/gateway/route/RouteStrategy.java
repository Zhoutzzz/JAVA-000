package gateway.route;

import java.util.List;

/**
 * @author ztz
 * @description TODO
 * @date 2020/11/3 22:31
 */
public interface RouteStrategy {

    String routeHost(List<String> endPointHost);
}
