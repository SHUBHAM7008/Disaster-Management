package com.example.Disaster.Management.Repositories;

import com.example.Disaster.Management.Tables.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends JpaRepository<Resource,Long> {

}
