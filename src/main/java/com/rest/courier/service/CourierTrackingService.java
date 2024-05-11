package com.rest.courier.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.courier.dao.CourierTrackingDao;
import com.rest.courier.dto.CourierTrackingRequestDTO;
import com.rest.courier.entity.CourierTracking;
import com.rest.courier.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourierTrackingService {

    private static final int MAX_METERS = 100;

    private static final int ONE_MINUTE = 60 * 1000;

    private static final double EARTH_RADIUS = 6371000;

    @Autowired
    private CourierTrackingDao courierTrackingDao;

    public void save(CourierTrackingRequestDTO courierTrackingRequestDTO) {
        List<Store> allStores = Store.getAllStores();
        for (Store store : allStores) {
            if (isAvailableForSave(courierTrackingRequestDTO, store)) {
                ObjectMapper mapper = new ObjectMapper();
                CourierTracking courierTracking = mapper.convertValue(courierTrackingRequestDTO, CourierTracking.class);
                courierTrackingDao.save(courierTracking);
            }
        }
    }

    private boolean isAvailableForSave(CourierTrackingRequestDTO courierTrackingRequestDTO, Store store) {
        float storeLatitude = store.getLat().floatValue();
        float storeLongitude = store.getLng().floatValue();
        Long requestCourier = courierTrackingRequestDTO.getCourier();
        Long requestTime = courierTrackingRequestDTO.getTime().getTime();
        Double requestLatitude = courierTrackingRequestDTO.getLatitude();
        Double requestLongitude = courierTrackingRequestDTO.getLongitude();
        float distance = distance(requestLatitude.floatValue(), requestLongitude.floatValue(), storeLatitude, storeLongitude);
        return distance <= MAX_METERS && isTimeLessThanOneMinute(requestCourier, requestLatitude, requestLongitude, requestTime);
    }

    private boolean isTimeLessThanOneMinute(Long courier, Double latitude, Double longitude, Long requestTime) {
        CourierTracking courierTracking = courierTrackingDao.findFirstByCourierAndLatitudeAndLongitudeOrderByTimeDesc(courier, latitude, longitude);
        if (courierTracking == null) {
            return true;
        }
        return courierTracking.getTime().getTime() < requestTime - ONE_MINUTE;
    }

    private float distance(float latitude1,
                           float longitude1,
                           float latitude2,
                           float longitude2) {
        double dLat = Math.toRadians(latitude2 - latitude1);
        double dLng = Math.toRadians(longitude2 - longitude1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return (float) (EARTH_RADIUS * c);
    }
}
