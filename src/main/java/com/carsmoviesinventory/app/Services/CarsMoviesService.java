package com.carsmoviesinventory.app.Services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public ResponseEntity<?> getAllMovies(Pageable pageable) {
        Page<CarsMoviesEntity> movies = carsMoviesRepository.findAll(pageable);
        return getResponseEntity(movies);
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

    public ResponseEntity<?> getMoviesByName(String movieName, Pageable pageable) {
        Page<CarsMoviesEntity> movies = carsMoviesRepository.findAllByCarMovieNameContaining(movieName, pageable);
        return getResponseEntity(movies);
    }

    private ResponseEntity<?> getResponseEntity(Page<CarsMoviesEntity> movies) {
        Map<String, Object> response = new HashMap<>();
        response.put("TotalElements", movies.getTotalElements());
        response.put("TotalPages", movies.getTotalPages());
        response.put("CurrentPage", movies.getNumber());
        response.put("NumberOfElements", movies.getNumberOfElements());
        response.put("Movies", movies.getContent());
        return ResponseEntity.ok(response);
    }

}