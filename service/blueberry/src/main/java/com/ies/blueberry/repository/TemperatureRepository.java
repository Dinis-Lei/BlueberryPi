package com.ies.blueberry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ies.blueberry.model.Temperature;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Long>{

}


