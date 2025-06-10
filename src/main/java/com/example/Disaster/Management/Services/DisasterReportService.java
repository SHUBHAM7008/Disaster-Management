package com.example.Disaster.Management.Services;

import com.example.Disaster.Management.Repositories.DisasterReportRepository;
import com.example.Disaster.Management.Tables.DisasterReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisasterReportService {

    @Autowired
    private DisasterReportRepository disasterReportRepository;

    public List<DisasterReport> getAllDisasterReports() {
        return disasterReportRepository.findAll();
    }

    public Optional<DisasterReport> getDisasterReportById(Long id) {
        return disasterReportRepository.findById(id);
    }

    public DisasterReport createDisasterReport(DisasterReport disasterReport) {
        return disasterReportRepository.save(disasterReport);
    }

    public DisasterReport updateDisasterReport(Long id, DisasterReport disasterReportDetails) {
        Optional<DisasterReport> disasterReport = disasterReportRepository.findById(id);

        if (disasterReport.isPresent()) {
            DisasterReport existingReport = disasterReport.get();
            existingReport.setName(disasterReportDetails.getName());
            existingReport.setDescription(disasterReportDetails.getDescription());
            existingReport.setLatitude(disasterReportDetails.getLatitude());
            existingReport.setLongitude(disasterReportDetails.getLongitude());

            return disasterReportRepository.save(existingReport);
        } else {
            return null; // Or throw an exception if you prefer
        }
    }

    public boolean deleteDisasterReport(Long id) {
        if (disasterReportRepository.existsById(id)) {
            disasterReportRepository.deleteById(id);
            return true;
        } else {
            return false; // Or throw an exception
        }
    }
}
