package com.ies.blueberry.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ies.blueberry.model.NetHarvest;

import java.util.List;
import java.util.Optional;

@Repository
public interface NetHarvestRepository extends JpaRepository<NetHarvest, Long> {
    List<Optional<Object>> findByLocation(String location, Pageable pageable);
    List<Optional<Object>> findByLocation(String location);
    List<Optional<Object>> findByLocationAndTimestampBetween(String location,Long begin,Long end, Pageable pageable);
    List<Optional<Object>> findByLocationAndTimestampBetween(String location,Long begin,Long end);
}
