package com.carsmoviesinventory.app.Controllers;

import com.carsmoviesinventory.app.Entities.GaseosasEntities;
import com.carsmoviesinventory.app.Services.GaseosasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/gaseosas")
public class GaseosasController {

    private final GaseosasService gaseosasService;

    @Autowired
    public GaseosasController(GaseosasService gaseosasService) {
        this.gaseosasService = gaseosasService;
    }

    // GET: Retrieve all gaseosas with pagination
    @GetMapping
    public ResponseEntity<?> getAllGaseosas(Pageable pageable) {
        return gaseosasService.getAllGaseosas(pageable);
    }

    // GET: Retrieve gaseosa by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getGaseosasById(@PathVariable UUID id) {
        return gaseosasService.getGaseosasById(id);
    }

    // GET: Retrieve gaseosas by name with pagination
    @GetMapping("/search")
    public ResponseEntity<?> getGaseosasByName(@RequestParam String gaseosaName, Pageable pageable) {
        return gaseosasService.getGaseosasByName(gaseosaName, pageable);
    }

    // POST: Add a new gaseosa
    @PostMapping
    public ResponseEntity<?> addGaseosa(@RequestBody GaseosasEntities gaseosa) {
        return gaseosasService.addGaseosa(gaseosa);
    }

    // PUT: Update an existing gaseosa by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateGaseosa(@PathVariable UUID id, @RequestBody GaseosasEntities gaseosa) {
        return gaseosasService.updateGaseosa(id, gaseosa);
    }

    // DELETE: Delete a gaseosa by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGaseosa(@PathVariable UUID id) {
        return gaseosasService.deleteGaseosa(id);
    }
}
