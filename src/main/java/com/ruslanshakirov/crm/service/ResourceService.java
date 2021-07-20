package com.ruslanshakirov.crm.service;

import com.ruslanshakirov.crm.entity.profile.Profile;
import com.ruslanshakirov.crm.entity.resource.Resource;
import com.ruslanshakirov.crm.entity.resource.ResourceType;
import com.ruslanshakirov.crm.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import static com.ruslanshakirov.crm.util.ValidationUtil.checkIdsMatch;
import static com.ruslanshakirov.crm.util.ValidationUtil.checkNotFound;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ResourceService {
    private final ResourceRepository resourceRepository;
    private final ProfileService profileService;

    public Page<Resource> findByType(ResourceType type, Pageable pageable) {
        Profile profile = profileService.findCurrentProfile();
        return resourceRepository.findByTypeAndProfile(type, profile, pageable);
    }

    public Resource findById(Long id) {
        Profile profile = profileService.findCurrentProfile();
        return checkNotFound(resourceRepository.findByIdAndProfile(id, profile), "Ресурс", id);
    }

    @Transactional
    public void remove(@PathVariable Long id) {
        Resource resource = findById(id);
        resourceRepository.delete(resource);
    }

    @Transactional
    public Resource create(Resource resource) {
        resource.setId(null);
        Profile profile = profileService.findCurrentProfile();
        resource.setProfile(profile);
        return resourceRepository.save(resource);
    }

    @Transactional
    public Resource update(Resource resource, Long id) {
        checkIdsMatch(resource, id);
        Profile profile = profileService.findCurrentProfile();
        resource.setProfile(profile);
        return resourceRepository.save(resource);
    }
}
