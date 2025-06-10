package com.example.Disaster.Management.Repositories;

import com.example.Disaster.Management.Tables.Emails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Emails,String> {
}
