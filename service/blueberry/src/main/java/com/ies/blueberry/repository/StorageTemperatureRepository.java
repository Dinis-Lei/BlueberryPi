package com.ies.blueberry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ies.blueberry.model.StorageTemperature;

import java.util.Optional;

@Repository
public interface StorageTemperatureRepository extends JpaRepository<StorageTemperature, Long>{
    Optional<StorageTemperature> findStorageTemperatureByLocation(String location);
}
