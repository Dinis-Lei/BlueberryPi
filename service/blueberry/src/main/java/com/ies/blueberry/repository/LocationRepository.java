package com.ies.blueberry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ies.blueberry.model.Location;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>{
    Optional<Location> findLocationByName(String name);
}

