package com.example.Disaster.Management.Services;


import com.example.Disaster.Management.Repositories.ResourceRepository;
import com.example.Disaster.Management.Tables.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    // Method to fetch all resources
    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    // Method to fetch resource by ID
    public Optional<Resource> getResourceById(Long id) {
        return resourceRepository.findById(id);
    }

    // Method to add a new resource
    public Resource addResource(Resource resource) {
        return resourceRepository.save(resource);
    }

    // Method to update an existing resource
    public Resource updateResource(Long id, Resource updatedResource) {
        if (resourceRepository.existsById(id)) {
            updatedResource.setId(id);  // Set the ID of the existing resource
            return resourceRepository.save(updatedResource);
        } else {
            return null;  // Resource not found, return null or throw exception as needed
        }
    }

    // Method to remove a resource by ID
    public void deleteResource(Long id) {
        resourceRepository.deleteById(id);
    }
}
