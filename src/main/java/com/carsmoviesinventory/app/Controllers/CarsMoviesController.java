package com.carsmoviesinventory.app.Controllers;

import com.carsmoviesinventory.app.Services.CarsMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/carsmovies")
public class CarsMoviesController{

    @Autowired
    private CarsMoviesService carsMoviesService;

    @GetMapping
    public ResponseEntity<?> getAllCarsMovies(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "carMovieName,asc") String[] sort) {
            Pageable pageable = PageRequest.of(page, size, Sort.by(parseSort(sort)));
            return carsMoviesService.getAllMovies(pageable);
        }

        private Sort.Order parseSort(String[] sort) {
            return new Sort.Order(Sort.Direction.fromString(sort[1]), sort[0]);
        }


    @GetMapping("/{id}")
    public ResponseEntity<?> getCarsMovieById(@PathVariable UUID id){
        return carsMoviesService.getMoviesById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getMoviesByName(
            @RequestParam String movieName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "carMovieName,asc") String[] sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(parseSort(sort)));
        return carsMoviesService.getMoviesByName(movieName, pageable);
    }

    @PostMapping
    public String insertCarsMovie(){
        return "Oye mi perro";
    }

    @PutMapping("/{id}")
    public String updateCarsMovie(){
        return "Oye mi perro";
    }

    @DeleteMapping("/{id}")
    public String deleteCarsMovie(){
        return "Oye mi perro";
    }

}