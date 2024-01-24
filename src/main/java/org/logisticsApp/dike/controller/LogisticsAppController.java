package org.logisticsApp.dike.controller;

import org.logisticsApp.dike.dtos.request.LoginRequest;
import org.logisticsApp.dike.dtos.request.OrderItemRequest;
import org.logisticsApp.dike.dtos.request.RegisterRequest;
import org.logisticsApp.dike.dtos.response.ApiResponse;
import org.logisticsApp.dike.dtos.response.LoginResponse;
import org.logisticsApp.dike.dtos.response.OrderItemResponse;
import org.logisticsApp.dike.dtos.response.RegisterResponse;
import org.logisticsApp.dike.exception.ApplicationLogisticsException;
import org.logisticsApp.dike.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogisticsAppController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<?>register(@RequestBody RegisterRequest registerRequest){
        RegisterResponse registerResponse = new RegisterResponse();
        try{
            customerService.register(registerRequest);
            registerResponse.setMessage("Account created successfully");
            return new ResponseEntity<>(new ApiResponse(true, registerResponse), HttpStatus.CREATED);
        }
        catch (ApplicationLogisticsException ex){
            return new ResponseEntity<>(new ApiResponse(false, registerResponse), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = new LoginResponse();
        try{
            customerService.login(loginRequest);
            loginResponse.setMessage("Login successfully");
            return new ResponseEntity<>(new ApiResponse(true, loginResponse), HttpStatus.CREATED);
        }
        catch (ApplicationLogisticsException ex){
            return new ResponseEntity<>(new ApiResponse(false, loginResponse), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/placeOrder")
    public ResponseEntity<?>placeOrder(@RequestBody OrderItemRequest orderItemRequest){
        OrderItemResponse orderItemResponse = new OrderItemResponse();
        try{
            customerService.placeOrder(orderItemRequest);
            orderItemResponse.setMessage("order placed successfully");
            return new ResponseEntity<>(new ApiResponse(true, orderItemResponse), HttpStatus.CREATED);
        }
        catch (ApplicationLogisticsException ex){
            return new ResponseEntity<>(new ApiResponse(false, orderItemResponse), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/placeOrderForAFriend")
    public ResponseEntity<?>placeOrderForAFriend(@RequestBody OrderItemRequest orderItemRequest){
        OrderItemResponse orderItemResponse = new OrderItemResponse();
        try{
            customerService.placeOrder(orderItemRequest);
            orderItemResponse.setMessage("friend's order placed successfully");
            return new ResponseEntity<>(new ApiResponse(true, orderItemResponse), HttpStatus.CREATED);
        }
        catch (ApplicationLogisticsException ex){
            return new ResponseEntity<>(new ApiResponse(false, orderItemResponse), HttpStatus.BAD_REQUEST);
        }
    }

}
