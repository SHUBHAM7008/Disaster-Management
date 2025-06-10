package com.example.Disaster.Management.Controllers;

import com.example.Disaster.Management.Services.IncidentService;
import com.example.Disaster.Management.Tables.Incident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/incidents")
public class IncidentController {

    @Autowired
    private IncidentService incidentService;

    @PostMapping
    public String createIncident(@ModelAttribute Incident incident) {
        Incident savedIncident = incidentService.saveOrUpdateIncident(incident);
        return "Success";
    }

    @GetMapping
    public ResponseEntity<List<Incident>> getAllIncidents() {
        List<Incident> incidents = incidentService.getAllIncidents();
        return ResponseEntity.ok(incidents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Incident> getIncidentById(@PathVariable Long id) {
        Optional<Incident> incident = incidentService.getIncidentById(id);
        return incident.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncident(@PathVariable Long id) {
        incidentService.deleteIncident(id);
        return ResponseEntity.noContent().build();
    }

    // Custom endpoints for filtering incidents
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Incident>> getIncidentsByStatus(@PathVariable String status) {
        List<Incident> incidents = incidentService.getIncidentsByStatus(status);
        return ResponseEntity.ok(incidents);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Incident>> getIncidentsByType(@PathVariable String type) {
        List<Incident> incidents = incidentService.getIncidentsByType(type);
        return ResponseEntity.ok(incidents);
    }

    @GetMapping("/count")
    public int count(){
        return incidentService.getAllIncidents().size();
    }
}

