package com.rest.courier.controller;

import com.rest.courier.dto.CourierTrackingRequestDTO;
import com.rest.courier.service.CourierTrackingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CourierTrackingController {

    @Autowired
    private CourierTrackingService courierTrackingService;

    @PostMapping(value = "/courierTracking", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> courierTracking(@Validated @RequestBody CourierTrackingRequestDTO courierTrackingRequestDTO) {
        try {
            courierTrackingService.save(courierTrackingRequestDTO);
        } catch (Exception e) {
            log.error("Error while saving courier tracking", e);
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok("success");
    }
}
