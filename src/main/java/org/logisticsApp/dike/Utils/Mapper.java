package org.logisticsApp.dike.Utils;

import org.logisticsApp.dike.data.model.Customer;
import org.logisticsApp.dike.dtos.request.RegisterRequest;

public class Mapper {
    public static Customer map(RegisterRequest registerRequest){
        Customer customer = new Customer();
        customer.setUsername(registerRequest.getUsername());
        customer.setPassword(registerRequest.getPassword());
        return customer;
    }
}
