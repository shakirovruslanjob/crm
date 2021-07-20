package com.ruslanshakirov.crm.service;

import com.ruslanshakirov.crm.dto.AgentProfileResponse;
import com.ruslanshakirov.crm.entity.profile.Agent;
import com.ruslanshakirov.crm.entity.profile.AgentType;
import com.ruslanshakirov.crm.entity.profile.Profile;
import com.ruslanshakirov.crm.mappers.ProfileMapper;
import com.ruslanshakirov.crm.repository.AgentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ruslanshakirov.crm.util.ValidationUtil.checkIdsMatch;
import static com.ruslanshakirov.crm.util.ValidationUtil.checkNotFound;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AgentService {
    private final AgentRepository agentRepository;
    private final ProfileService profileService;
    private final ProfileMapper profileMapper;

    public Page<Agent> findByTypeAndCurrentProfile(AgentType type, Pageable pageable) {
        Profile profile = profileService.findCurrentProfile();

        return agentRepository.findByAgentTypeAndProfile(type, profile, pageable);
    }

    public Agent findByIdAndCurrentProfile(Long id) {
        Profile profile = profileService.findCurrentProfile();
        return checkNotFound(agentRepository.findByIdAndProfile(id, profile), "Контрагент", id);
    }

    @Transactional
    public void remove(Long id) {
        Agent agent = findByIdAndCurrentProfile(id);
        agentRepository.delete(agent);
    }

    @Transactional
    public Agent create(AgentProfileResponse counterAgentProfile) {
        Agent agent = profileMapper.toEntity(counterAgentProfile);
        Profile profile = profileService.findCurrentProfile();
        agent.setProfile(profile);
        return agentRepository.save(agent);
    }

    @Transactional
    public Agent update(AgentProfileResponse agentProfileDto, Long id) {
        checkIdsMatch(agentProfileDto, id);
        Agent agent = findByIdAndCurrentProfile(id);
        profileMapper.toEntity(agentProfileDto, agent);
        return agent;
    }
}
