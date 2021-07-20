package com.ruslanshakirov.crm.controller;

import com.ruslanshakirov.crm.dto.ProfileResponse;
import com.ruslanshakirov.crm.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final ProfileService profileService;

    @PatchMapping("/current-profile")
    public ProfileResponse changeCurrentProfile(@RequestParam Long profileId) {
        return profileService.changeCurrentProfile(profileId);
    }
}
