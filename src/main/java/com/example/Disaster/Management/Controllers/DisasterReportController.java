package com.example.Disaster.Management.Controllers;
import com.example.Disaster.Management.Repositories.EmailRepository;
import com.example.Disaster.Management.Services.DisasterReportService;
import com.example.Disaster.Management.Services.EmailService;
import com.example.Disaster.Management.Tables.DisasterReport;
import com.example.Disaster.Management.Tables.Emails;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/disaster-reports")
public class DisasterReportController {

    @Autowired
    private DisasterReportService disasterReportService;

    @Autowired
    EmailService emailService;

    @Autowired
    EmailRepository emailRepository;

    @GetMapping
    public ResponseEntity<List<DisasterReport>> getAllDisasterReports() {
        List<DisasterReport> reports = disasterReportService.getAllDisasterReports();
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisasterReport> getDisasterReportById(@PathVariable Long id) {
        Optional<DisasterReport> report = disasterReportService.getDisasterReportById(id);
        return report.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

//    @PostMapping
//    public ResponseEntity<DisasterReport> createDisasterReport(@RequestBody DisasterReport disasterReport) {
//        DisasterReport createdReport = disasterReportService.createDisasterReport(disasterReport);
//        List<Emails> list = emailRepository.findAll().stream().toList();
//        List<String> list1= new ArrayList<>();
//
//        for(Emails e :list){
//            list1.add(e.getMail());
//            System.out.println("My Mail"+e.getMail());
//        }
//        emailService.sendAlertEmail(list1,disasterReport.getDescription(),disasterReport.getName());
//        return new ResponseEntity<>(createdReport, HttpStatus.CREATED);
//    }


    @PostMapping
    public ResponseEntity<DisasterReport> createDisasterReport(@RequestBody DisasterReport disasterReport) {
        DisasterReport createdReport = disasterReportService.createDisasterReport(disasterReport);

        // Get all emails from the repository
        List<Emails> list = emailRepository.findAll().stream().toList();

        // Filter out invalid email addresses
        List<String> validEmailAddresses = list.stream()
                .map(Emails::getMail)
                .filter(this::isValidEmail)
                .collect(Collectors.toList());

        // If there are any invalid emails, log them
        list.stream()
                .map(Emails::getMail)
                .filter(email -> !isValidEmail(email))
                .forEach(email -> System.out.println("Invalid email address: " + email));

        // Send email alert only to valid emails
        emailService.sendAlertEmail(validEmailAddresses, disasterReport.getDescription(), disasterReport.getName());

        return new ResponseEntity<>(createdReport, HttpStatus.CREATED);
    }

    // Helper method to validate email addresses
    private boolean isValidEmail(String email) {
        try {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate(); // Will throw exception if email is invalid
            return true;
        } catch (AddressException ex) {
            return false;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisasterReport> updateDisasterReport(@PathVariable Long id, @RequestBody DisasterReport disasterReportDetails) {
        DisasterReport updatedReport = disasterReportService.updateDisasterReport(id, disasterReportDetails);
        if (updatedReport != null) {
            return new ResponseEntity<>(updatedReport, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisasterReport(@PathVariable Long id) {
        boolean deleted = disasterReportService.deleteDisasterReport(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
