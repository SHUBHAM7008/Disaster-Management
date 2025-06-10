package com.example.Disaster.Management.Repositories;

import com.example.Disaster.Management.Tables.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter,Long>
{}
