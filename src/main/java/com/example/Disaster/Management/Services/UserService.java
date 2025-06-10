package com.example.Disaster.Management.Services;

import com.example.Disaster.Management.Tables.User;
import com.example.Disaster.Management.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create or update a user
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Get a user by id
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Get a user by email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Get all users
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Delete a user by id
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    // Check if a user exists by email
    public boolean userExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public String getCurrentUser() {
        // Get the authentication object
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // If the user is authenticated, return the username or any other info
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();  // The username of the authenticated user
        }
        return "Guest";  // If not authenticated, return "Guest"
    }
    public String getCurrentUserEmail() {
        // Get the OAuth2 authentication token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            OAuth2User oauthUser = oauthToken.getPrincipal();
            return oauthUser.getAttribute("email");  // Get email from OAuth2 provider
        }
        return "Guest";  // If not authenticated
    }
}
