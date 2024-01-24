package org.logisticsApp.dike.data.repository;

import org.logisticsApp.dike.data.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository <Customer, String>{
    Customer findCustomerByUsername(String username);
}
