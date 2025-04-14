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
public class GaseosasService {

    private final GaseosasRepository gaseosasRepository;

    public GaseosasService(GaseosasRepository gaseosasRepository) {
        this.gaseosasRepository = gaseosasRepository;
    }

    public ResponseEntity<?> getAllGaseosas(Pageable pageable) {
        Page<GaseosasEntities> gaseosas = gaseosasRepository.findAll(pageable);
        return getResponseEntity(gaseosas);
    }

    public ResponseEntity<?> getGaseosasById(UUID id) {
        Optional<GaseosasEntities> gaseosa = gaseosasRepository.findById(id);
        if (gaseosa.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("Status", String.format("Gaseosa with ID %s not found.", id));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(Collections.singletonMap("Gaseosa", gaseosa.get()));
    }

    public ResponseEntity<?> getGaseosasByName(String gaseosaName, Pageable pageable) {
        Page<GaseosasEntities> gaseosas = gaseosasRepository.findAllByGaseosaNameContaining(gaseosaName, pageable);
        return getResponseEntity(gaseosas);
    }

    private ResponseEntity<?> getResponseEntity(Page<GaseosasEntities> gaseosas) {
        Map<String, Object> response = new HashMap<>();
        response.put("TotalElements", gaseosas.getTotalElements());
        response.put("TotalPages", gaseosas.getTotalPages());
        response.put("CurrentPage", gaseosas.getNumber());
        response.put("NumberOfElements", gaseosas.getNumberOfElements());
        response.put("Gaseosas", gaseosas.getContent());
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> addGaseosa(GaseosasEntities gaseosaToAdd) {
        Page<GaseosasEntities> gaseosa = gaseosasRepository.findAllByGaseosaNameContaining(
                gaseosaToAdd.getGaseosaName(),
                Pageable.unpaged());
        if (gaseosa.getTotalElements() > 0) {
            return new ResponseEntity<>(Collections.singletonMap("Status", String.format("Gaseosa already exists with %d coincidences.", gaseosa.getTotalElements())), HttpStatus.CONFLICT);
        } else {
            GaseosasEntities savedGaseosa = gaseosasRepository.save(gaseosaToAdd);
            return new ResponseEntity<>(Collections.singletonMap("Status", String.format("Added Gaseosa with ID %s", savedGaseosa.getId())), HttpStatus.CREATED);
        }
    }

    public ResponseEntity<?> updateGaseosa(UUID id, GaseosasEntities gaseosaToUpdate) {
        Optional<GaseosasEntities> gaseosa = gaseosasRepository.findById(id);
        if (gaseosa.isEmpty()) {
            return new ResponseEntity<>(Collections.singletonMap("Status", String.format("Gaseosa with ID %s not found.", id)), HttpStatus.NOT_FOUND);
        }
        GaseosasEntities existingGaseosa = gaseosa.get();

        existingGaseosa.setGaseosaName(gaseosaToUpdate.getGaseosaName());
        existingGaseosa.setGaseosaSabor(gaseosaToUpdate.getGaseosaSabor());
        existingGaseosa.setEmpresa(gaseosaToUpdate.getEmpresa());

        gaseosasRepository.save(existingGaseosa);

        return ResponseEntity.ok(Collections.singletonMap("Status", String.format("Updated Gaseosa with ID %s", existingGaseosa.getId())));
    }

    public ResponseEntity<?> deleteGaseosa(UUID id) {
        Optional<GaseosasEntities> gaseosa = gaseosasRepository.findById(id);
        if (gaseosa.isEmpty()) {
            return new ResponseEntity<>(Collections.singletonMap("Status", String.format("Gaseosa with ID %s doesn't exist.", id)), HttpStatus.NOT_FOUND);
        }
        gaseosasRepository.deleteById(id);
        return ResponseEntity.ok(Collections.singletonMap("Status", String.format("Deleted Gaseosa with ID %s", id)));
    }
}
