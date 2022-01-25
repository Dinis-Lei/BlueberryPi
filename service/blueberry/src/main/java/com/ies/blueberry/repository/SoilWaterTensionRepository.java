package com.ies.blueberry.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ies.blueberry.model.SoilWaterTension;

import java.util.List;
import java.util.Optional;

@Repository
public interface SoilWaterTensionRepository extends JpaRepository<SoilWaterTension, Long>{
    List<Optional<Object>> findByLocation(String location,Pageable page);
    List<Optional<Object>> findByLocationAndTimestampBetween(String location,Long begin,Long end,Pageable page);

}
