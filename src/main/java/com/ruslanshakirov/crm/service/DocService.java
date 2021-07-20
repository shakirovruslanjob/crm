package com.ruslanshakirov.crm.service;

import com.ruslanshakirov.crm.entity.document.Doc;
import com.ruslanshakirov.crm.entity.document.DocStatus;
import com.ruslanshakirov.crm.entity.profile.Profile;
import com.ruslanshakirov.crm.mappers.DocMapper;
import com.ruslanshakirov.crm.repository.DocRepository;
import com.ruslanshakirov.crm.repository.specs.DocSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.ruslanshakirov.crm.util.ValidationUtil.checkNotFound;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DocService {
    private final DocRepository docRepository;
    private final ProfileService profileService;
    private final DocMapper docMapper;

    @Transactional
    public List<Doc> filter(DocStatus status, LocalDate startDate, LocalDate endDate, String agentName) {
        Profile profile = profileService.findCurrentProfile();
        Specification<Doc> specification = DocSpec.filter(status, startDate, endDate, agentName, profile);
        return docRepository.findAll(specification);
    }

    @Transactional
    public Doc create(Doc doc) {
        Profile profile = profileService.findCurrentProfile();
        doc.setProfile(profile);
        return docRepository.save(doc);
    }

    @Transactional
    public Doc update(Doc doc, Long id) {
        Doc dbDoc = findByIdAndCurrentProfile(id);
        docMapper.toEntity(doc, dbDoc);
        return docRepository.save(doc);
    }

    @Transactional
    public void delete(Long id) {
        docRepository.delete(findByIdAndCurrentProfile(id));
    }

    @Transactional
    public Doc findByIdAndCurrentProfile(Long id) {
        Profile profile = profileService.findCurrentProfile();
        return checkNotFound(docRepository.findByIdAndProfile(id, profile), "Документ", id);
    }
}
