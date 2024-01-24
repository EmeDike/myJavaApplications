package org.logisticsApp.dike.dtos.request;

import lombok.Data;

@Data

public class OrderItemRequest {
    private String type;
    private String description;
    private String number;
    private String customerName;

}
