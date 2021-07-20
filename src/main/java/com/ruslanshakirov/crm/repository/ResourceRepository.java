package com.ruslanshakirov.crm.repository;

import com.ruslanshakirov.crm.entity.profile.Profile;
import com.ruslanshakirov.crm.entity.resource.Resource;
import com.ruslanshakirov.crm.entity.resource.ResourceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ResourceRepository extends PagingAndSortingRepository<Resource, Long> {
    Page<Resource> findByTypeAndProfile(ResourceType type, Profile profile, Pageable pageable);

    Optional<Resource> findByIdAndProfile(Long id, Profile profile);
}
