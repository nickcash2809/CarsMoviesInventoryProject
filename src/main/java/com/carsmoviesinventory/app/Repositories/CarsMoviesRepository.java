package com.carsmoviesinventory.app.Repositories;

import com.carsmoviesinventory.app.Entities.CarsMoviesEntity;
import java.util.UUID;


public interface CarsMoviesRepository extends JpaRepository<CarsMoviesEntity, UUID>{

}