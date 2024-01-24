package org.logisticsApp.dike.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class ExtendedUser {
    @Id
    private String id;
    private String name;
    private String postalCode;
    private String houseNumber;
    private String city;
    private String state;
    private String country;
}
