package com.example.Disaster.Management.Services;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailedService implements UserDetailsService {

    // Example method for loading user from DB (you need to implement the DB logic)
    @Override
    public UserDetails loadUserByUsername(String username) {
        // Example user details for simplicity
        // You should fetch user from the database based on the username
        // You may have additional fields and authorities

        // Here, I'm assuming a user is found with the username. Replace with actual DB query.
        if ("testuser".equals(username)) {
            return User.builder()
                    .username(username)
                    .password("{bcrypt}$2a$10$e1HcT/QlWJlXhxvq0Im6YuZnZKrlUqLxK3Fm9ZL9yTFCVG6ABpA62") // bcrypt encoded password
                    .roles("USER") // Add roles/authorities here
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
