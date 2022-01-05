package com.ies.blueberry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ies.blueberry.model.StorageHumidity;

import java.util.Optional;

@Repository
public interface StorageHumidityRepository extends JpaRepository<StorageHumidity, Long>{
    Optional<StorageHumidity> findStorageHumidityByLocation(String location);
}
