package com.ies.blueberry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ies.blueberry.model.Alert;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long>{
    List<Alert> findByLocationAndSensor(String location, String sensor);
}
