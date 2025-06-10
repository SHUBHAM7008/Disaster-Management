package com.example.Disaster.Management.Services;

import com.example.Disaster.Management.Repositories.ReqResourceRepository;
import com.example.Disaster.Management.Tables.ReqResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReqResourceService {

    @Autowired
    private ReqResourceRepository reqResourceRepository;

    public List<ReqResource> getAllReqResources() {
        return reqResourceRepository.findAll();
    }

    public Optional<ReqResource> getReqResourceById(Long id) {
        return reqResourceRepository.findById(id);
    }

    public ReqResource createReqResource(ReqResource reqResource) {
        return reqResourceRepository.save(reqResource);
    }

    public ReqResource updateReqResource(Long id, ReqResource reqResourceDetails) {
        Optional<ReqResource> reqResource = reqResourceRepository.findById(id);

        if (reqResource.isPresent()) {
            ReqResource existingResource = reqResource.get();
            existingResource.setType(reqResourceDetails.getType());
            existingResource.setQuantity(reqResourceDetails.getQuantity());
            return reqResourceRepository.save(existingResource);
        } else {
            return null; // Or throw an exception if you prefer
        }
    }

    public boolean deleteReqResource(Long id) {
        if (reqResourceRepository.existsById(id)) {
            reqResourceRepository.deleteById(id);
            return true;
        } else {
            return false; // Or throw an exception
        }
    }
}
