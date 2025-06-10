package com.example.Disaster.Management.Controllers;

import com.example.Disaster.Management.Repositories.EmailRepository;
import com.example.Disaster.Management.Services.UserService;
import com.example.Disaster.Management.Tables.Emails;
import com.example.Disaster.Management.Tables.Login;
import com.example.Disaster.Management.Tables.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailRepository emailRepository;

    @RequestMapping("/")
    public String dashboard(){
        Emails  emails = new Emails();
        emails.setMail(useremail());
       emailRepository.save(emails);
        return "UserDash";
    }

    @PostMapping("/login")
    public String userLogin(@ModelAttribute Login credentials) {
        System.out.println("Login attempt with username: " + credentials.getUsername());
        // For example, this is how you can validate the user (hardcoded or from a DB)
        // credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));
        User user = this.userService.getUserByEmail(credentials.getUsername()).orElse(null);
        // System.out.println(user);
        if(user!=null && credentials.getPassword().equals(user.getPassword())) {
            System.out.println(user);

            if(user.getRole().toString().equalsIgnoreCase("admin"))
                return "redirect:/admin/";
            else if(user.getRole().toString().equalsIgnoreCase("end user"))
                return "redirect:/users/";
            return "redirect:/test/home";
        }
        return "redirect:/test/login";
    }

    @RequestMapping("/currentuser")
    public String user(){
        return userService.getCurrentUser();
    }
    @RequestMapping("/currentuseremail")
    public String useremail(){
        return userService.getCurrentUserEmail();
    }

    //precaution page
    @RequestMapping("/earth")
    public String earth(){
        return "earthquakepre";
    }

    @RequestMapping("/flood")
    public String flood(){
        return "floodpre";
    }

    @RequestMapping("/tornado")
    public String tornado(){
        return "tornado";
    }

    @RequestMapping("/wild")
    public String wild(){
        return "wildfire";
    }

}
