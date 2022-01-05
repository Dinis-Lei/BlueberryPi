package com.ies.blueberry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ies.blueberry.model.UnitLoss;
import java.util.Optional;

@Repository
public interface UnitLossRepository extends JpaRepository<UnitLoss, Long>{
    Optional<UnitLoss> findUnitLossByLocation(String location);
}
