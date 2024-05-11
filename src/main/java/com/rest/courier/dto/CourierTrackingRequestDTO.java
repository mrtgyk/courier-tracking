package com.rest.courier.dto;

import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class CourierTrackingRequestDTO {
    @NonNull
    Date time;
    @NonNull
    Long courier;
    @NonNull
    Double latitude;
    @NonNull
    Double longitude;
}
