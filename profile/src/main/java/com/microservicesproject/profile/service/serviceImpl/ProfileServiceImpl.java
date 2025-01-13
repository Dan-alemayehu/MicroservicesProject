package com.microservicesproject.profile.service.serviceImpl;

import com.microservicesproject.profile.exceptions.ResourceNotFoundException;
import com.microservicesproject.profile.model.Profile;
import com.microservicesproject.profile.repository.ProfileRepository;
import com.microservicesproject.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Override
    public Profile createProfile(Profile profile){
        validateUsername(profile.getUsername());
        return profileRepository.save(profile);
    }

    @Override
    public Profile getProfileById(Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id: " + id));
    }

    @Override
    public Profile getProfileByUsername(String username) {
        return profileRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with username: " + username));
    }

    @Override
    public Profile updateProfile(Long id, Map<String, Object> profile){
        Profile existingProfile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id: " + id ));
        profile.forEach((key, value) ->{
            switch(key) {
                case "username":
                    if(value != null && !value.toString().isBlank()){
                        validateUsername(value.toString());
                        existingProfile.setUsername(value.toString());
                    }
                    break;
                case "password":
                    if(value != null){
                        existingProfile.setPassword(value.toString());
                    }
                    break;
                case "email":
                    if(value != null){
                        existingProfile.setEmail(value.toString());
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });
        return profileRepository.save(existingProfile);
    }

    @Override
    public void deleteProfile(Long id){
        if(!profileRepository.existsById(id)){
            throw new ResourceNotFoundException("Profile not found with id: " + id);
        }
        profileRepository.deleteById(id);
    }

    @Override
    public boolean usernameExists(String username){
        return profileRepository.findByUsername(username).isPresent();
    }

    //Helper Methods
    private void validateUsername(String username){
        if(username == null || username.isBlank()){
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if(usernameExists(username)){
            throw new IllegalArgumentException("Username: " + username + " already exists");
        }
    }
}