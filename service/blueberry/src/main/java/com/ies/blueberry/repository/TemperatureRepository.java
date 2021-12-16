package com.ies.blueberry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ies.blueberry.model.Temperature;
import java.util.Optional;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Long>{
    Optional<Temperature> findTemperatureByLocation(String emailId);
}


