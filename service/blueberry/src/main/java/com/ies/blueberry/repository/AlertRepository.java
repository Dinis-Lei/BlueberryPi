package com.ies.blueberry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ies.blueberry.model.Alert;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long>{

    List<Alert> findByLocationAndSensorAndStartGreaterThanEqualAndEndLessThanEqual(String location, String sensor, Long start, Long end);

    List<Alert> findByLocationAndSensorAndSeenAndStartGreaterThanEqualAndEndLessThanEqual(String location, String sensor, Boolean seen, Long start, Long end);

    List<Alert> findByStartGreaterThanEqualAndEndLessThanEqual(Long start, Long end);

    List<Alert> findBySeenAndStartGreaterThanEqualAndEndLessThanEqual(Boolean seen, Long start, Long end); 
    
    List<Alert> findByLocationAndStartGreaterThanEqualAndEndLessThanEqual(String location, Long start, Long end);

    List<Alert> findByLocationAndSeenAndStartGreaterThanEqualAndEndLessThanEqual(String location, Boolean seen, Long start, Long end);

    List<Alert> findByLocationAndSensor(String location, String sensor);

    List<Alert> findAll();
}
