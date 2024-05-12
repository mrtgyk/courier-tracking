package com.rest.courier.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.courier.dao.CourierTrackingDao;
import com.rest.courier.dto.CourierTrackingRequestDTO;
import com.rest.courier.entity.CourierTracking;
import com.rest.courier.model.Store;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CourierTrackingService {

    private static final int MAX_METERS = 100;

    private static final int ONE_MINUTE = 60 * 1000;

    @Autowired
    private CourierTrackingDao courierTrackingDao;

    @Autowired
    private DistanceCalculateService distanceCalculateService;

    public void save(CourierTrackingRequestDTO courierTrackingRequestDTO) {
        List<Store> allStores = Store.getAllStores();
        for (Store store : allStores) {
            float distance = distanceCalculateService.distance(courierTrackingRequestDTO.getLatitude().floatValue(),
                    courierTrackingRequestDTO.getLongitude().floatValue(),
                    store.getLat().floatValue(),
                    store.getLng().floatValue());

            boolean isTimeLessThanOneMinute = isTimeLessThanOneMinute(courierTrackingRequestDTO.getCourier(),
                    courierTrackingRequestDTO.getLatitude(),
                    courierTrackingRequestDTO.getLongitude(),
                    courierTrackingRequestDTO.getTime().getTime());

            if (distance <= MAX_METERS && isTimeLessThanOneMinute) {
                ObjectMapper mapper = new ObjectMapper();
                CourierTracking courierTracking = mapper.convertValue(courierTrackingRequestDTO, CourierTracking.class);
                courierTracking.setDistance((int) distance);
                courierTrackingDao.save(courierTracking);
                log.info("Courier Tracking saved. Courier: {}, Time: {}", courierTracking.getCourier(), courierTracking.getTime());
            }
        }
    }

    private boolean isTimeLessThanOneMinute(Long courier, Double latitude, Double longitude, Long requestTime) {
        CourierTracking courierTracking = courierTrackingDao.findFirstByCourierAndLatitudeAndLongitudeOrderByTimeDesc(courier, latitude, longitude);
        if (courierTracking == null) {
            return true;
        }
        return courierTracking.getTime().getTime() < requestTime - ONE_MINUTE;
    }
}
