package org.logisticsApp.dike.services;

import org.logisticsApp.dike.data.model.OrderItem;
import org.logisticsApp.dike.dtos.request.OrderItemRequest;

public interface OrderItemService {
    void placeOrder();

    OrderItem placeOrder(OrderItemRequest orderItemRequest, String id);

}
