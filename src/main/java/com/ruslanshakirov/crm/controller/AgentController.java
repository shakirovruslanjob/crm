package com.ruslanshakirov.crm.controller;

import com.ruslanshakirov.crm.entity.profile.Agent;
import com.ruslanshakirov.crm.entity.profile.AgentType;
import com.ruslanshakirov.crm.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class AgentController {
    private final AgentService profileService;

    @GetMapping
    public List<Agent> findAll(@RequestParam AgentType type,
                               @PageableDefault(sort = "name", size = 30) Pageable pageable) throws InterruptedException {
        return profileService.findByTypeAndCurrentProfile(type, pageable).getContent();
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        profileService.remove(id);
    }
}
