package org.logisticsApp.dike.dtos.request;

import lombok.Data;

@Data
public class ExtendedUserRequest {
    private String name;
    private String postalCode;
    private String houseNumber;
    private String city;
    private String state;
    private String country;
}
