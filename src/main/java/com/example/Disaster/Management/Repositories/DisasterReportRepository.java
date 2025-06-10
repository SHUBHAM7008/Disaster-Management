package com.example.Disaster.Management.Repositories;


import com.example.Disaster.Management.Tables.DisasterReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisasterReportRepository extends JpaRepository<DisasterReport, Long> {
    // You can add custom query methods here if needed
    // For example:
    // List<DisasterReport> findByNameContaining(String name);
}

