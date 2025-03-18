package com.carsmoviesinventory.app.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.carsmoviesinventory.app.Repositories.CarsMoviesRepository;
import com.carsmoviesinventory.app.Entities.CarsMoviesEntity;

import java.util.*;

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

    public ResponseEntity<?> getMoviesById(UUID id){
        Optional<CarsMoviesEntity> movie = carsMoviesRepository.findById(id);
        if(movie.isPresent()){
            Map<String, Object> response = new HashMap<>();
            response.put("Movie", movie.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}