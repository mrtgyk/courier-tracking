package com.rest.courier.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CourierTrackingRequestDTO {
    Date time;
    String courier;
    Long latitude;
    Long longitude;
}
