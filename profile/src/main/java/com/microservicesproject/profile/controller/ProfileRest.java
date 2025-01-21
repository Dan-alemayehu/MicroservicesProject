package com.microservicesproject.profile.controller;

import com.microservicesproject.profile.model.Profile;
import com.microservicesproject.profile.service.ProfileService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/profiles")
@Slf4j
@RequiredArgsConstructor
public class ProfileRest {

    private final ProfileService profileService;

    @GetMapping("/{id}")
//    @CircuitBreaker(name = "profile", fallbackMethod = "fallbackMethod")
    public ResponseEntity<Profile> getProfileById(@PathVariable Long id) {
        log.info("Get profile with id {}", id);
        return ResponseEntity.ok(profileService.getProfileById(id));
    }

    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles() {
        List<Profile> profiles = profileService.getAllProfiles();
        if (profiles.isEmpty()) {
            // Return a 204 No Content if no profiles are found
            return ResponseEntity.noContent().build();
        }
        // Return the profiles with a 200 OK status
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Profile> getProfileByUsername(@PathVariable String username) {
        log.info("Get profile with username {}", username);
        return ResponseEntity.ok(profileService.getProfileByUsername(username));
    }

    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
        log.info("Create profile {}", profile.getUsername());
        Profile createdProfile = profileService.createProfile(profile);
        return ResponseEntity.status(201).body(createdProfile);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable Long id, @RequestBody Map<String, Object> profile) {
        log.info("Updating profile with id {}", id);
        Profile updatedProfile = profileService.updateProfile(id, profile);
        return ResponseEntity.ok(updatedProfile);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        log.info("Deleting profile with id {}", id);
        profileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/exists/{username}")
    public ResponseEntity<Boolean> usernameExists(@PathVariable String username) {
        log.info("Checking if username {} exists", username);
        return ResponseEntity.ok(profileService.usernameExists(username));
    }

    //CircuitBreaker FallBack Method
//    public String fallbackMethod(Long id, RuntimeException runtimeException) {
//        return "Oops! Something went wrong!! Please try again after some time";
//    }
}