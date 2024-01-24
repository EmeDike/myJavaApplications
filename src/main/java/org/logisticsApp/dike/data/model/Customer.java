package org.logisticsApp.dike.data.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Customer {
    private String id;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private String username;
    private String password;
    private boolean isLocked = true;
}
