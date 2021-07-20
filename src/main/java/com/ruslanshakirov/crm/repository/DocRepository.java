package com.ruslanshakirov.crm.repository;

import com.ruslanshakirov.crm.entity.document.Doc;
import com.ruslanshakirov.crm.entity.profile.Profile;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface DocRepository extends PagingAndSortingRepository<Doc, Long>, JpaSpecificationExecutor<Doc> {
    Optional<Doc> findByIdAndProfile(Long id, Profile profile);
}

