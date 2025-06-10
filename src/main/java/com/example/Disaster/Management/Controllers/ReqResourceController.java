package com.example.Disaster.Management.Controllers;

import com.example.Disaster.Management.Services.ReqResourceService;
import com.example.Disaster.Management.Tables.ReqResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/req-res")
public class ReqResourceController {

    @Autowired
    private ReqResourceService reqResourceService;

    @GetMapping
    public ResponseEntity<List<ReqResource>> getAllReqResources() {
        List<ReqResource> resources = reqResourceService.getAllReqResources();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReqResource> getReqResourceById(@PathVariable Long id) {
        Optional<ReqResource> resource = reqResourceService.getReqResourceById(id);
        return resource.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ReqResource> createReqResource( @RequestBody ReqResource reqResource) {
        ReqResource createdResource = reqResourceService.createReqResource(reqResource);
        return new ResponseEntity<>(createdResource, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReqResource> updateReqResource(@PathVariable Long id, @RequestBody ReqResource reqResourceDetails) {
        ReqResource updatedResource = reqResourceService.updateReqResource(id, reqResourceDetails);
        if (updatedResource != null) {
            return new ResponseEntity<>(updatedResource, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReqResource(@PathVariable Long id) {
        boolean deleted = reqResourceService.deleteReqResource(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
