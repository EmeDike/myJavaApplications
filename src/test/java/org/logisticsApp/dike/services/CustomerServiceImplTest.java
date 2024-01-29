package org.logisticsApp.dike.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.logisticsApp.dike.data.model.ExtendedUser;
import org.logisticsApp.dike.data.repository.CustomerRepository;
import org.logisticsApp.dike.data.repository.OrderItemRepository;
import org.logisticsApp.dike.dtos.request.LoginRequest;
import org.logisticsApp.dike.dtos.request.OrderItemRequest;
import org.logisticsApp.dike.dtos.request.RegisterRequest;
import org.logisticsApp.dike.exception.UserExistException;
import org.logisticsApp.dike.exception.InvlidDetailsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest


class CustomerServiceImplTest {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @BeforeEach
    public void doThisAfterEachTest(){
        customerRepository.deleteAll();
        orderItemRepository.deleteAll();

    }
    @Test
    public void registerACustomer_RegisterSameCustomerAgain_ThrowException(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPassword("dikedivine");
        registerRequest.setUsername("dike");
        customerService.register(registerRequest);
        assertThrows(UserExistException.class, ()-> customerService.register(registerRequest));
    }
    @Test
    public void  loginWithWrongPasswordAfterRegistringUser_throwsException(){
        RegisterRequest registerRequest = new RegisterRequest();
        LoginRequest loginRequest = new LoginRequest();
        registerRequest.setPassword("dikedivine");
        registerRequest.setUsername("dike");
        customerService.register(registerRequest);

        registerRequest.setPassword("dikemelody");
        registerRequest.setUsername("dikedivine");
        assertThrows(InvlidDetailsException.class, ()-> customerService.login(loginRequest));

    }
    @Test
    public void testThatWhenACustomerPlacesAnOrder_OrderIncreases(){
        RegisterRequest registerRequest = new RegisterRequest();
        LoginRequest loginRequest = new LoginRequest();
        registerRequest.setPassword("dikedivine");
        registerRequest.setUsername("dike");
        customerService.register(registerRequest);
        loginRequest.setPassword("dikedivine");
        loginRequest.setUsername("dike");
        OrderItemRequest orderItemRequest = new OrderItemRequest();
        orderItemRequest.setType("food");
        orderItemRequest.setDescription("coke");
        orderItemRequest.setCustomerName("dike");
        customerService.placeOrder(orderItemRequest);
        System.out.println(orderItemRepository.count());
        assertEquals(1,orderItemRepository.count());
    }
    @Test
    public void testThatACustomerCanPlaceOrderMoreThanOnce(){
        RegisterRequest registerRequest = new RegisterRequest();
        LoginRequest loginRequest = new LoginRequest();
        registerRequest.setPassword("dikedivine");
        registerRequest.setUsername("dike");
        customerService.register(registerRequest);
        loginRequest.setPassword("dikedivine");
        loginRequest.setUsername("dike");
        OrderItemRequest orderItemRequest = new OrderItemRequest();
        orderItemRequest.setType("food");
        orderItemRequest.setDescription("coke");
        orderItemRequest.setCustomerName("dike");
        customerService.placeOrder(orderItemRequest);
        orderItemRequest.setType("cloth");
        orderItemRequest.setDescription("pant");
        orderItemRequest.setCustomerName("dike");
        customerService.placeOrder(orderItemRequest);
        assertEquals(2,orderItemRepository.count());

    }
    @Test
    public void testThatACustomerCanPlaceOrderForAFriend() {
        RegisterRequest registerRequest = new RegisterRequest();
        LoginRequest loginRequest = new LoginRequest();
        registerRequest.setPassword("dikedivine");
        registerRequest.setUsername("dike");
        customerService.register(registerRequest);
        loginRequest.setPassword("dikedivine");
        loginRequest.setUsername("dike");
        OrderItemRequest orderItemRequest = new OrderItemRequest();
        orderItemRequest.setType("food");
        orderItemRequest.setDescription("coke");
        orderItemRequest.setCustomerName("dike");
        customerService.placeOrder(orderItemRequest);
        ExtendedUser extendedUser = new ExtendedUser();
        extendedUser.setName("divine");
        extendedUser.setHouseNumber("4 agbeji rd");
        extendedUser.setCity("yaba");
        extendedUser.setState("lagos");
        extendedUser.setCountry("nigeria");
        assertEquals(1,orderItemRepository.count());
    }

}