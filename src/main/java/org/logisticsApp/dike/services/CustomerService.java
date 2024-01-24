package org.logisticsApp.dike.services;

import org.logisticsApp.dike.data.model.ExtendedUser;
import org.logisticsApp.dike.dtos.request.LoginRequest;
import org.logisticsApp.dike.dtos.request.OrderItemRequest;
import org.logisticsApp.dike.dtos.request.RegisterRequest;

public interface CustomerService {
    void register(RegisterRequest registerRequest);
    void login(LoginRequest loginRequest);
    void placeOrder(OrderItemRequest orderItemRequest);

    void placeOrderForFriend(OrderItemRequest orderItemRequest, ExtendedUser extendedUser);
}
