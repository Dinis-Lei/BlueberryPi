package com.ies.blueberry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ies.blueberry.model.SoilPH;

import java.util.Optional;

@Repository
public interface SoilPHRepository extends JpaRepository<SoilPH, Long>{
    Optional<SoilPH> findSoilPHByLocation(String location);
}
