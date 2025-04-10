package com.carsmoviesinventory.app.Services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.carsmoviesinventory.app.Repositories.GaseosasRepository;
import com.carsmoviesinventory.app.Entities.GaseosasEntities;

import java.util.*;

@Service
public class GaseosasService{

    private final GaseosasRepository GaseosasRepository;

    public GaseosasService(GaseosasRepository GaseosasRepository) {
        this.GaseosasRepository = GaseosasRepository;
    }

    public ResponseEntity<?> getAllGaseosas(Pageable pageable) {
        Page<GaseosasEntities> Gaseosas = GaseosasRepository.findAll(pageable);
        return getResponseEntity(Gaseosas);
    }

    public ResponseEntity<?> getGaseosasById(UUID id) {
        Optional<GaseosasEntities> Gaseosa = GaseosasRepository.findById(id);
        if (Gaseosa.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("Status", String.format("Gaseosa with ID %s not found.", id));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(Collections.singletonMap("Movie", Gaseosa.get()));
    }


    public ResponseEntity<?> getGaseosasByName(String GaseosaName, Pageable pageable) {
        Page<GaseosasEntities> Gaseosas = GaseosasRepository.findAllByGaseosaNameContaining(GaseosaName, pageable);
        return getResponseEntity(Gaseosas);
    }

    private ResponseEntity<?> getResponseEntity(Page<GaseosasEntities> Gaseosas) {
        Map<String, Object> response = new HashMap<>();
        response.put("TotalElements", Gaseosas.getTotalElements());
        response.put("TotalPages", Gaseosas.getTotalPages());
        response.put("CurrentPage", Gaseosas.getNumber());
        response.put("NumberOfElements",Gaseosas.getNumberOfElements());
        response.put("Gaseosas", Gaseosas.getContent());
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> addMovie(GaseosasEntities movieToAdd) {
        Page<GaseosasEntities> movie = carsMoviesRepository.findAllByCarMovieNameContaining(
                movieToAdd.getCarMovieName(),
                Pageable.unpaged());
        if (movie.getTotalElements() > 0) {
            return new ResponseEntity<>(Collections.singletonMap("Status", String.format("Movie already exists with %d coincidences.", movie.getTotalElements())), HttpStatus.CONFLICT);
        } else {
            GaseosasEntities savedMovie = carsMoviesRepository.save(movieToAdd);
            return new ResponseEntity<>(Collections.singletonMap("Status", String.format("Added Movie with ID %s", savedMovie.getId())), HttpStatus.CREATED);
        }
    }

    public ResponseEntity<?> updateGaseosa(UUID id, GaseosasEntities GaseosaToUpdate) {
        Optional<GaseosasEntities> Gaseosa = GaseosasRepository.findById(id);
        if (Gaseosa.isEmpty()) {
            return new ResponseEntity<>(Collections.singletonMap("Status", String.format("Movie with ID %s not found.", id)), HttpStatus.NOT_FOUND);
        }
        GaseosasEntities existingGaseosa = Gaseosa.get();

        existingGaseosa.setCarMovieName(GaseosaToUpdate.getGaseosaName());
        existingGaseosa.setCarMovieYear(GaseosaToUpdate.getGaseosaSabor());
        existingGaseosa.setDuration(GaseosaToUpdate.getEmpresa());

        carsMoviesRepository.save(existingMovie);

        return ResponseEntity.ok(Collections.singletonMap("Status", String.format("Updated Movie with ID %s", existingMovie.getId())));
    }

    public ResponseEntity<?> deleteMovie(UUID id) {
        Optional<GaseosasEntities> movie = carsMoviesRepository.findById(id);
        if (movie.isEmpty()) {
            return new ResponseEntity<>(Collections.singletonMap("Status", String.format("Movie with ID %s doesn't exist.", id)),HttpStatus.NOT_FOUND);
        }
        carsMoviesRepository.deleteById(id);
        return ResponseEntity.ok(Collections.singletonMap("Status", String.format("Deleted Movie with ID %s", id)));
    }

}