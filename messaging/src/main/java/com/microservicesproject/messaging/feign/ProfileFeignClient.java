package com.microservicesproject.messaging.feign;

import com.microservicesproject.messaging.dto.ProfileDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "profile-service", path = "/api/profiles")
public interface ProfileFeignClient {

    @GetMapping("/{id}")
    ProfileDto getProfileById(@PathVariable Long id);

    @GetMapping("/username/{username}")
    ProfileDto getProfileByUsername(@PathVariable String username);
}
