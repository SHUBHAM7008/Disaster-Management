package com.example.Disaster.Management.Repositories;

import com.example.Disaster.Management.Tables.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository<Incident,Long> {

    @Query("SELECT i FROM Incident i WHERE i.status = :status")
    List<Incident> findIncidentsByStatus(String status);

    @Query("SELECT i FROM Incident i WHERE i.type = :type")
    List<Incident> findIncidentsByType(String type);
}