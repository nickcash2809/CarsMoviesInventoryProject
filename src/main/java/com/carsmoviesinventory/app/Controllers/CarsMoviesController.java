package com.carsmoviesinventory.app.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/api/v1/carsmovies")
public class CarsMoviesController{

    @GetMapping
    public String getAllCarsMovies(){
        return "Oye mi perro";
    }

    @GetMapping("/{id}")
    public String getCarsMovieById(){
        return "Oye mi perro";
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