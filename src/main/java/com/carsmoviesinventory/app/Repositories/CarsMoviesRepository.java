package com.carsmoviesinventory.app.Repositories;

import com.carsmoviesinventory.app.Entities.CarsMoviesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;


public interface CarsMoviesRepository extends JpaRepository<CarsMoviesEntity, UUID>{

}