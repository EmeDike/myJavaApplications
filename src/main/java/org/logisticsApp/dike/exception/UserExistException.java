package org.logisticsApp.dike.exception;

import org.logisticsApp.dike.exception.ApplicationLogisticsException;

public class UserExistException extends ApplicationLogisticsException {
    public UserExistException(String message){
        super("message");
    }
}
