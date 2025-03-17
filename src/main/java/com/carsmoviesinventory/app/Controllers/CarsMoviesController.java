package com.carsmoviesinventory.app.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/api/v1/carsmovies")
public Class CarsMoviesController{

    @GetMapping
    public String getAllCarsMovies(){

    }

    @GetMapping("/{id}")
    public String getCarsMovieById(){

    }

    @PostMapping
    public String insertCarsMovie(){
        
    }

    @PutMapping("/{id}")
    public String updateCarsMovie(){
        
    }

    @DeleteMapping("/{id}")
    public String deleteCarsMovie(){
        
    }

}