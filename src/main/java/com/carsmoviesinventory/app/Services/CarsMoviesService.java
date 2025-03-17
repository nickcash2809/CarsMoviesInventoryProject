package com.carsmoviesinventory.app.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carsmoviesinventory.app.Repositories.CarsMoviesRepository;
import com.carsmoviesinventory.app.Entities.CarsMoviesEntity;
import java.util.List;

@Service
public class CarsMoviesService{

    @Autowired
    private CarsMoviesRepository carsMoviesRepository;

    public List<CarsMoviesEntity> getAllMovies(){
        return carsMoviesRepository.findAll();
    }
}