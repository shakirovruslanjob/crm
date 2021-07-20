package com.ruslanshakirov.crm.repository;

import com.ruslanshakirov.crm.entity.profile.Agent;
import com.ruslanshakirov.crm.entity.profile.AgentType;
import com.ruslanshakirov.crm.entity.profile.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface AgentRepository extends PagingAndSortingRepository<Agent, Long> {
    @EntityGraph(attributePaths = "bankAccounts")
    Page<Agent> findByAgentTypeAndProfile(AgentType counterAgentType, Profile profile, Pageable pageable);

    Optional<Agent> findByIdAndProfile(Long id, Profile profile);
}
