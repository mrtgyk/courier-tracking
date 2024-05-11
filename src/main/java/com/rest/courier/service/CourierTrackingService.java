package com.rest.courier.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.courier.dao.CourierTrackingDao;
import com.rest.courier.dto.CourierTrackingRequestDTO;
import com.rest.courier.entity.CourierTracking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourierTrackingService {

    @Autowired
    private CourierTrackingDao courierTrackingDao;

    public void save(CourierTrackingRequestDTO courierTrackingRequestDTO) {
        ObjectMapper mapper = new ObjectMapper();
        CourierTracking courierTracking = mapper.convertValue(courierTrackingRequestDTO, CourierTracking.class);
        courierTrackingDao.save(courierTracking);
    }
}
