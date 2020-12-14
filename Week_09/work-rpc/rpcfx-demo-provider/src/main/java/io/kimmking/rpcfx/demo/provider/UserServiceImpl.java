package io.kimmking.rpcfx.demo.provider;

import io.kimmking.rpcfx.client.RpcRequest;
import io.kimmking.rpcfx.demo.api.User;
import io.kimmking.rpcfx.demo.api.UserService;

public class UserServiceImpl implements UserService {

    @Override
    @RpcRequest
    public User findById(int id) {
        return new User(id, "KK" + System.currentTimeMillis());
    }
}
