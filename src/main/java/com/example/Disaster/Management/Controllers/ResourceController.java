package com.example.Disaster.Management.Controllers;


import com.example.Disaster.Management.Services.ResourceService;
import com.example.Disaster.Management.Tables.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    // Endpoint to get all resources
    @GetMapping
    public List<Resource> getAllResources() {
        return resourceService.getAllResources();
    }

    // Endpoint to get resource by ID
    @GetMapping("/{id}")
    public ResponseEntity<Resource> getResourceById(@PathVariable Long id) {
        Optional<Resource> resource = resourceService.getResourceById(id);
        return resource.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint to add a new resource
    @PostMapping
    public String addResource(@ModelAttribute Resource resource) {
        Resource savedResource = resourceService.addResource(resource);
        return "Success";
    }

    // Endpoint to update an existing resource
    @PutMapping("/{id}")
    public ResponseEntity<Resource> updateResource(@PathVariable Long id, @ModelAttribute Resource updatedResource) {
        Resource resource = resourceService.updateResource(id, updatedResource);
        return resource != null ? ResponseEntity.ok(resource) : ResponseEntity.notFound().build();
    }

    // Endpoint to delete a resource
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable Long id) {
        resourceService.deleteResource(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public int count(){
        return resourceService.getAllResources().size();
    }
}

