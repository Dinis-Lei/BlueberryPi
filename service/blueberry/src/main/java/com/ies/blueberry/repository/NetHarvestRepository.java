package com.ies.blueberry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ies.blueberry.model.NetHarvest;
import java.util.Optional;

@Repository
public interface NetHarvestRepository extends JpaRepository<NetHarvest, Long>{
    Optional<NetHarvest> findNetHarvestByLocation(String location);
}
