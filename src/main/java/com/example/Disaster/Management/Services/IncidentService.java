package com.example.Disaster.Management.Services;

import com.example.Disaster.Management.Repositories.IncidentRepository;
import com.example.Disaster.Management.Tables.Incident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidentService {

    @Autowired
    private IncidentRepository incidentRepository;

    // Create or Update an incident
    public Incident saveOrUpdateIncident(Incident incident) {
        return incidentRepository.save(incident);
    }

    // Get all incidents
    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    // Get an incident by ID
    public Optional<Incident> getIncidentById(Long id) {
        return incidentRepository.findById(id);
    }

    // Delete an incident
    public void deleteIncident(Long id) {
        incidentRepository.deleteById(id);
    }

    // Custom query examples (optional)
    public List<Incident> getIncidentsByStatus(String status) {
        // Custom method can be added in the repository, like findByStatus()
        return incidentRepository.findIncidentsByStatus(status);
    }

    public List<Incident> getIncidentsByType(String type) {
        // Custom method for filtering by type
        return incidentRepository.findIncidentsByType(type);
    }
}

