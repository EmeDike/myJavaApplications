package org.logisticsApp.dike.services;

import org.logisticsApp.dike.data.model.OrderItem;
import org.logisticsApp.dike.data.repository.OrderItemRepository;
import org.logisticsApp.dike.dtos.request.OrderItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class OrderItemServiceImpl implements OrderItemService{
    @Autowired
    private OrderItemRepository orderItemRepository;

    private String[] orderType = new String[5];
    private int size;
    @Override
    public void placeOrder() {

    }

    @Override
    public void placeOrder(OrderItemRequest orderItemRequest, String id) {
        OrderItem orderItem = new OrderItem();
        orderItem.setType(orderItemRequest.getType());
        orderItem.setNumber(orderItemRequest.getNumber());
        orderItem.setDescription(orderItemRequest.getDescription());
        orderItem.setCustomerid(id);
        orderItemRepository.save(orderItem);
    }
}
