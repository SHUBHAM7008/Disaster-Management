package com.example.Disaster.Management.Services;


import com.example.Disaster.Management.Repositories.VolunteerRepository;
import com.example.Disaster.Management.Tables.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VolunteerService {

    @Autowired
    private VolunteerRepository volunteerRepository;

    // Method to fetch all volunteers
    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    // Method to fetch a volunteer by ID
    public Optional<Volunteer> getVolunteerById(Long id) {
        return volunteerRepository.findById(id);
    }

    // Method to add a new volunteer
    public Volunteer addVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    // Method to update an existing volunteer
    public Volunteer updateVolunteer(Long id, Volunteer updatedVolunteer) {
        if (volunteerRepository.existsById(id)) {
            updatedVolunteer.setId(id);  // Set the ID of the existing volunteer
            return volunteerRepository.save(updatedVolunteer);
        } else {
            return null;  // Volunteer not found, return null or throw exception as needed
        }
    }

    // Method to remove a volunteer by ID
    public void deleteVolunteer(Long id) {
        volunteerRepository.deleteById(id);
    }
}

