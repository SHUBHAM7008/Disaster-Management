package com.example.Disaster.Management.Services;

import com.example.Disaster.Management.Repositories.ShelterRepository;
import com.example.Disaster.Management.Tables.Shelter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ShelterService {

    @Autowired
    private ShelterRepository shelterRepository;

    // Add a new Shelter
    public Shelter addShelter(Shelter shelter) {
        return shelterRepository.save(shelter);
    }

    // Update an existing Shelter
    public Shelter updateShelter(Long id, Shelter shelterDetails) {
        Shelter shelter = shelterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shelter not found with id: " + id));

        shelter.setName(shelterDetails.getName());
        shelter.setLocation(shelterDetails.getLocation());
        shelter.setCapacity(shelterDetails.getCapacity());
        shelter.setAvailableCapacity(shelterDetails.getAvailableCapacity());
        shelter.setContact(shelterDetails.getContact());

        return shelterRepository.save(shelter);
    }

    // Get Shelter by ID
    public Shelter getShelterById(Long id) {
        return shelterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shelter not found with id: " + id));
    }

    // Get all Shelters
    public List<Shelter> getAllShelters() {
        return shelterRepository.findAll();
    }

    // Delete Shelter by ID
    public void deleteShelter(Long id) {
        Shelter shelter = shelterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shelter not found with id: " + id));
        shelterRepository.delete(shelter);
    }
}
