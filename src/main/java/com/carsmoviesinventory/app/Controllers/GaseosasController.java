package com.carsmoviesinventory.app.Controllers;

import com.carsmoviesinventory.app.Entities.GaseosasEntities;
import com.carsmoviesinventory.app.Services.GaseosasService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/Gaseosas")
@Validated
public class GaseosasController{

    private final GaseosasService GaseosasService;

    public GaseosasController(GaseosasService GaseosasService) {
        this.GaseosasService = GaseosasService;
    }

    @GetMapping
    public ResponseEntity<?> getAllGaseosas(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "GaseosaName,asc") String[] sort) {
        try {
                Pageable pageable = PageRequest.of(page, size, Sort.by(parseSort(sort)));
                return GaseosasService.getAllGaseosas(pageable);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body("Invalid sorting direction. Use 'asc' or 'desc'.");
            }
        }

    private Sort.Order parseSort(String[] sort) {
        if (sort.length < 2) {
            throw new IllegalArgumentException("Sort parameter must have both field and direction (e.g., 'carMovieYear,desc').");
        }

        String property = sort[0];
        String direction = sort[1].toLowerCase();

        List<String> validDirections = Arrays.asList("asc", "desc");
        if (!validDirections.contains(direction)) {
            throw new IllegalArgumentException("Invalid sort direction. Use 'asc' or 'desc'.");
        }

        return new Sort.Order(Sort.Direction.fromString(direction), property);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getGaseosasById(@PathVariable UUID id){
        return GaseosasService.getGaseosasById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getGaseosasByName(
            @RequestParam String GaseosaName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "GaseosaName,asc") String[] sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(parseSort(sort)));
        return GaseosasService.getGaseosasByName(GaseosaName, pageable);
    }

    @PostMapping
    public ResponseEntity<?> insertGaseosa(@Valid @RequestBody GaseosasEntities GaseosasEntities){
        return GaseosasService.addGaseosa(GaseosasEntities);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGaseosa(@PathVariable UUID id, @Valid @RequestBody GaseosasEntities GaseosasEntities){
        return GaseosasService.updateGaseosa(id,GaseosasEntities);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGaseosa(@PathVariable UUID id){
        return GaseosasService.deleteGaseosa(id);
    }

}