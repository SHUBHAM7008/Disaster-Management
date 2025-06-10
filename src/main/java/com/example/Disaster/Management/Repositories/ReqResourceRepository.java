package com.example.Disaster.Management.Repositories;

import com.example.Disaster.Management.Tables.ReqResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReqResourceRepository extends JpaRepository<ReqResource, Long> {
    // You can add custom query methods here if needed
}
