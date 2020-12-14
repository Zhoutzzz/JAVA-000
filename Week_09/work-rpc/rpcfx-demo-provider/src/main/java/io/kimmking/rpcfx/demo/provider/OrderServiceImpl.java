package io.kimmking.rpcfx.demo.provider;

import io.kimmking.rpcfx.client.RpcRequest;
import io.kimmking.rpcfx.demo.api.Order;
import io.kimmking.rpcfx.demo.api.OrderService;

public class OrderServiceImpl implements OrderService {

    @Override
    @RpcRequest
    public Order findOrderById(int id) {
        return new Order(id, "Cuijing" + System.currentTimeMillis(), 9.9f);
    }
}
