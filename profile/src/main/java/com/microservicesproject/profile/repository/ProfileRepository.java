package com.microservicesproject.profile.repository;

import com.microservicesproject.profile.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Query("SELECT p FROM Profile p WHERE p.id != :currentUserId")
    List<Profile> findAllOtherProfiles(@Param("currentUserId") Long userId);
    Optional<Profile> findByUsername(String username);
}
