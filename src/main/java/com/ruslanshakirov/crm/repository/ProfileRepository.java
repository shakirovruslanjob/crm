package com.ruslanshakirov.crm.repository;

import com.ruslanshakirov.crm.entity.User;
import com.ruslanshakirov.crm.entity.profile.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends PagingAndSortingRepository<Profile, Long> {
    Optional<Profile> findByIdAndUser(Long id, User user);

    List<Profile> findAllByUser(User user);
}
