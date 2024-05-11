package com.rest.courier.dao;

import com.rest.courier.entity.CourierTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierTrackingDao extends JpaRepository<CourierTracking, Long> {
}
