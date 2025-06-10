package com.example.Disaster.Management.Controllers;

import com.example.Disaster.Management.Services.ShelterService;
import com.example.Disaster.Management.Tables.Shelter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shelters")
public class ShelterController{

    @Autowired
    private ShelterService shelterService;

    // Add a new Shelter
    @PostMapping
    public ResponseEntity<String> createShelter(@ModelAttribute Shelter shelter) {
        Shelter createdShelter = shelterService.addShelter(shelter);
        String htmlResponse = "<script>window.location.href='/admin/shelters';</script>";
        return ResponseEntity.ok(htmlResponse);

    }

    // Update an existing Shelter
    @PutMapping("/{id}")
    public ResponseEntity<Shelter> updateShelter(@PathVariable Long id, @ModelAttribute Shelter shelter) {
        Shelter updatedShelter = shelterService.updateShelter(id, shelter);
        return ResponseEntity.ok(updatedShelter);
    }

    // Get Shelter by ID
    @GetMapping("/{id}")
    public ResponseEntity<Shelter> getShelterById(@PathVariable Long id) {
        Shelter shelter = shelterService.getShelterById(id);
        return ResponseEntity.ok(shelter);
    }

    // Get all Shelters
    @GetMapping
    public List<Shelter> getAllShelters() {
        return shelterService.getAllShelters();
    }

    // Delete Shelter by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShelter(@PathVariable Long id) {
        shelterService.deleteShelter(id);
        return new ResponseEntity<>("Shelter deleted successfully", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/count")
    public int count(){
        return shelterService.getAllShelters().size();
    }
}
