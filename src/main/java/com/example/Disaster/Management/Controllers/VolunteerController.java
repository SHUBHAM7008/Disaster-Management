package com.example.Disaster.Management.Controllers;

import com.example.Disaster.Management.Services.VolunteerService;
import com.example.Disaster.Management.Tables.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/volunteers")
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;

    // Endpoint to get all volunteers
    @GetMapping
    public List<Volunteer> getAllVolunteers() {
        System.out.println(volunteerService.getAllVolunteers());
        return volunteerService.getAllVolunteers();
    }

    // Endpoint to get a volunteer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Volunteer> getVolunteerById(@PathVariable Long id) {
        Optional<Volunteer> volunteer = volunteerService.getVolunteerById(id);
        return volunteer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint to add a new volunteer
    @PostMapping
    public ResponseEntity<String> addVolunteer(@ModelAttribute Volunteer volunteer) {
        volunteerService.addVolunteer(volunteer);
        String htmlResponse = "<script>window.location.href='/admin/volunteers';</script>";
        return ResponseEntity.ok(htmlResponse);
    }

    // Endpoint to update an existing volunteer
    @PutMapping("/{id}")
    public ResponseEntity<Volunteer> updateVolunteer(@PathVariable Long id, @ModelAttribute Volunteer updatedVolunteer) {
        Volunteer volunteer = volunteerService.updateVolunteer(id, updatedVolunteer);
        return volunteer != null ? ResponseEntity.ok(volunteer) : ResponseEntity.notFound().build();
    }

    // Endpoint to delete a volunteer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVolunteer(@PathVariable Long id) {
        volunteerService.deleteVolunteer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public int count(){
        System.out.println(volunteerService.getAllVolunteers().size()+"");
        return volunteerService.getAllVolunteers().size();
    }
}
