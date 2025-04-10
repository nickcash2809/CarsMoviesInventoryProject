package com.carsmoviesinventory.app.Repositories;

import com.carsmoviesinventory.app.Entities.GaseosasEntities;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GaseosasRepository extends JpaRepository<GaseosasEntities, UUID>{

    Page<GaseosasEntities> findAllByGaseosaNameContaining(String GaseosaName, Pageable pageable);

    @Override
    Page<GaseosasEntities> findAll(Pageable pageable);
}