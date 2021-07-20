package com.ruslanshakirov.crm.controller;

import com.ruslanshakirov.crm.dto.ProfileResponse;
import com.ruslanshakirov.crm.entity.profile.Profile;
import com.ruslanshakirov.crm.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/profiles/personal")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping
    public List<ProfileResponse> findByCurrentUser() {
        return profileService.findByCurrentUser();
    }

    @GetMapping("/{id}")
    public ProfileResponse findById(@PathVariable Long id) {
        return profileService.findByIdAndCurrentUser(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        profileService.removeByIdAndCurrentUser(id);
    }

    @PostMapping
    public ResponseEntity<ProfileResponse> create(Profile profile) {
        ProfileResponse created = profileService.create(profile);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{id}")
    public ProfileResponse update(@PathVariable Long id, Profile profile) {
        return profileService.update(profile, id);
    }
}
