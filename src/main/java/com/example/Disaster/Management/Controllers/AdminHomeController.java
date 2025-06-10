package com.example.Disaster.Management.Controllers;

import com.example.Disaster.Management.Tables.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/admin")
public class AdminHomeController {

    @RequestMapping("/")
    public String Home(){
        return "AdminHome";
    }

    @RequestMapping("/incidents")
    public String incidentPage(){
        return "Incident";
    }

    @RequestMapping("/resources")
    public String resourcePage(){
        return "Resource";
    }

    @RequestMapping("/shelters")
    public String shelterPage(){
        return "Shelter";
    }

    @RequestMapping("/volunteers")
    public String volunteerPage(){
        return "Volunteer";
    }

    @RequestMapping("/loginPage")
        public String loginPage() {
            return "AdminLogin";
        }

    @PostMapping("/login")
    public String dash(@ModelAttribute Login login){
        if(login.getUsername().equalsIgnoreCase("Shubham") && login.getPassword().equals("Errors")){
            return "AdminHome";
        }
        return "/";
    }

    @RequestMapping("/noti")
    public String Noti(){
        return "Notifications";
    }

    @RequestMapping("/disaster-report")
    public String report(){
        return "DisasterReport";
    }

    @RequestMapping("/ReqResources")
    public String request(){
        return "ReqResource";
    }
}
