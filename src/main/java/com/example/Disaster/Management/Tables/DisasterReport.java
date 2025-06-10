package com.example.Disaster.Management.Tables;


import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
@Table(name = "disaster_reports")
public class DisasterReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")  // Use TEXT for longer descriptions
    private String description;

    @Column(name = "latitude", precision = 10, scale = 8) // Adjust precision/scale as needed
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 11, scale = 8) // Adjust precision/scale as needed
    private BigDecimal longitude;

    // Constructors (No-args, All-args)
    public DisasterReport() {
    }

    public DisasterReport(String name, String description, BigDecimal latitude, BigDecimal longitude) {
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    // toString() method (optional, but helpful for debugging)
    @Override
    public String toString() {
        return "DisasterReport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
