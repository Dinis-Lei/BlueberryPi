package com.ies.blueberry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ies.blueberry.model.PlantationTemperature;
import java.util.Optional;

@Repository
public interface PlantationTemperatureRepository extends JpaRepository<PlantationTemperature, Long>{
    Optional<PlantationTemperature> findPlantationTemperatureByLocation(String location);
}


