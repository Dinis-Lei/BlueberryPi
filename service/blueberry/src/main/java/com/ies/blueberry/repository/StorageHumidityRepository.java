package com.ies.blueberry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ies.blueberry.model.StorageHumidity;

import java.util.List;
import java.util.Optional;

@Repository
public interface StorageHumidityRepository extends JpaRepository<StorageHumidity, Long>{
    List<Optional<Object>> findByLocation(String location);
    List<Optional<Object>> findByLocationAndTimestampBetween(String location,Long begin,Long end);

}
