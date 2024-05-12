package com.rest.courier.model;

import java.util.List;

public final class Store {
    private final String name;
    private final Double lat;
    private final Double lng;

    public Store(String name, Double lat, Double lng) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    public static List<Store> getAllStores() {
        return List.of(
                new Store("Ataşehir MMM Migros", 40.9923307, 29.1244229),
                new Store("Novada MMM Migros", 40.986106, 29.1161293),
                new Store("Beylikdüzü 5M Migros", 41.0066851, 28.6552262),
                new Store("Ortaköy MMM Migros", 41.055783, 29.0210292),
                new Store("Caddebostan MMM Migros", 40.9632463, 29.06309084)
        );
    }
}
