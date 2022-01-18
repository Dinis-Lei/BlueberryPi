package com.ies.blueberry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ies.blueberry.model.Location;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>{
    // @Query(value = "SELECT * FROM location where name = :name",nativeQuery = true)
    // Optional<Location> findLocationByName(@Param("name") String name);
    Optional<Location> findLocationByName(String name);
 }

