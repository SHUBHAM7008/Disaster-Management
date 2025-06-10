package com.example.Disaster.Management.Repositories;

import com.example.Disaster.Management.Tables.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer,Long> {

}