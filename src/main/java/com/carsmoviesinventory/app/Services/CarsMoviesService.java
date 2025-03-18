package com.carsmoviesinventory.app.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.carsmoviesinventory.app.Repositories.CarsMoviesRepository;
import com.carsmoviesinventory.app.Entities.CarsMoviesEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CarsMoviesService{

    private final CarsMoviesRepository carsMoviesRepository;

    public CarsMoviesService(CarsMoviesRepository carsMoviesRepository) {
        this.carsMoviesRepository = carsMoviesRepository;
    }

    public ResponseEntity<Map<String, Object>> getAllMovies(){
        Map<String, Object> response = new HashMap<>();
        List<CarsMoviesEntity> movies = carsMoviesRepository.findAll();
        response.put("Total", movies.size());
        response.put("Movies", movies);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public CarsMoviesEntity getMoviesById(UUID id){
        return carsMoviesRepository.findById(id).orElse(null);
    }
}