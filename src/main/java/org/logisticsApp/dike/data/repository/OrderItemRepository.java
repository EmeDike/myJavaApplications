package org.logisticsApp.dike.data.repository;

import org.logisticsApp.dike.data.model.OrderItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderItemRepository extends MongoRepository<OrderItem, String> {
    OrderItem findOrderItemBy(String type);
}
