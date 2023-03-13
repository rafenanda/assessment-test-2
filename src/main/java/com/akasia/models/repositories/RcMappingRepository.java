package com.akasia.models.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akasia.models.entities.RcMapping;

@Repository
public interface RcMappingRepository extends JpaRepository<RcMapping, String> {
    Optional<RcMapping> findById(String rc);
}
