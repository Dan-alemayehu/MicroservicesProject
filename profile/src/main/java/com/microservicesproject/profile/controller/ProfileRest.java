package com.microservicesproject.profile.controller;

import com.microservicesproject.profile.model.Profile;
import com.microservicesproject.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/profiles")
@Slf4j
@RequiredArgsConstructor
public class ProfileRest {

    private final ProfileService profileService;

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfile(@PathVariable Long id) {
        log.info("Get profile with id {}", id);
        return ResponseEntity.ok(profileService.getProfileById(id));
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
}