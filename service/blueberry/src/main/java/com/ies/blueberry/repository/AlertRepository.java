package com.ies.blueberry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ies.blueberry.model.Alert;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long>{
    List<Alert> findByLocationAndSensor(String location, String sensor);

    List<Alert> findByLocationAndSensorAndStartAfterAndEndBefore(String location, String sensor, Long start, Long end);

    List<Alert> findByLocationAndSensorAndSeen(String location, String sensor, Boolean seen);

    List<Alert> findByLocationAndSensorAndSeenAndStartAfterAndEndBefore(String location, String sensor, Boolean seen, Long start, Long end);

    List<Alert> findBySeen(Boolean seen);

    List<Alert> findAll();
}
