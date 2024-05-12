package com.rest.courier.service;

import org.springframework.stereotype.Service;

@Service
public class DistanceCalculateService {

    private static final double EARTH_RADIUS = 6371000;

    public float distance(float latitude1,
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
