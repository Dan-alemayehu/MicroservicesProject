package com.microservicesproject.profile.service;

import com.microservicesproject.profile.model.Profile;

import java.util.Map;
import java.util.Optional;

public interface ProfileService {

    Profile createProfile(Profile profile);

    Profile getProfileById(Long id);

    Profile getProfileByUsername(String username);

    Profile updateProfile(Long id, Map<String, Object> profile);

    void deleteProfile(Long id);

    boolean usernameExists(String username);
}
