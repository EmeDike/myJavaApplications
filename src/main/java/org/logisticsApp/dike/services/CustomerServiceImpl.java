package org.logisticsApp.dike.services;

import org.logisticsApp.dike.data.model.Customer;
import org.logisticsApp.dike.data.model.ExtendedUser;
import org.logisticsApp.dike.data.model.OrderItem;
import org.logisticsApp.dike.data.repository.CustomerRepository;
import org.logisticsApp.dike.data.repository.OrderItemRepository;
import org.logisticsApp.dike.dtos.request.LoginRequest;
import org.logisticsApp.dike.dtos.request.OrderItemRequest;
import org.logisticsApp.dike.dtos.request.RegisterRequest;
import org.logisticsApp.dike.exception.InvlidDetailsException;
import org.logisticsApp.dike.exception.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.logisticsApp.dike.Utils.Mapper.map;

@Service

public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Override
    public void register(RegisterRequest registerRequest) {
        if (userExist(registerRequest.getUsername()))throw new UserExistException(registerRequest.getUsername()+ "already exist");
        Customer customer = map(registerRequest) ;
        customerRepository.save(customer);

    }

    private boolean userExist(String username) {
        Customer foundCustomer = customerRepository.findCustomerByUsername(username);
        return foundCustomer != null;
    }

    @Override
    public void login(LoginRequest loginRequest) {
        Customer foundCustomer = customerRepository.findCustomerByUsername(loginRequest.getUsername());
        if(!userExist(loginRequest.getUsername())) throw new InvlidDetailsException(loginRequest.getPassword()+ "invalid details");
        if(!foundCustomer.getPassword().equals(loginRequest.getPassword())) throw new InvlidDetailsException(loginRequest.getPassword()+ "invalid details");
        foundCustomer.setLocked(false);
        customerRepository.save(foundCustomer);
    }

    @Override
    public void placeOrder(OrderItemRequest orderItemRequest) {
        Customer customer = customerRepository.findCustomerByUsername(orderItemRequest.getCustomerName());
        OrderItem orderItem = null;
        if(customer != null){
            orderItem = orderItemService.placeOrder(orderItemRequest, customer.getId());
        }

        if (customer == null) {
            throw new RuntimeException("customer not found");
        }

        orderItemRepository.save(orderItem);



    }

    @Override
    public void placeOrderForFriend(OrderItemRequest orderItemRequest, ExtendedUser extendedUser) {

    }
}
