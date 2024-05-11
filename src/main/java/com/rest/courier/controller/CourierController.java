package com.rest.courier.controller;

import com.rest.courier.dto.CourierTrackingRequestDTO;
import com.rest.courier.dto.CourierTrackingResponseDTO;
import com.rest.courier.service.CourierTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourierController {

    @Autowired
    private CourierTrackingService courierTrackingService;

    @PostMapping(value = "/courierTracking")
    public ResponseEntity<CourierTrackingResponseDTO> courierTracking(@RequestBody CourierTrackingRequestDTO courierTrackingRequestDTO) {
        courierTrackingService.save(courierTrackingRequestDTO);
        return ResponseEntity.ok().build();
    }
}
