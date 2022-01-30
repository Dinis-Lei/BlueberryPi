package com.ies.blueberry.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ies.blueberry.model.PlantationTemperature;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlantationTemperatureRepository extends JpaRepository<PlantationTemperature, Long>{
    List<Optional<Object>> findByLocation(String location,Pageable page);
    List<Optional<Object>> findByLocationAndTimestampBetween(String location,Long begin,Long end, Pageable page);

}


