package com.ruslanshakirov.crm.service;

import com.ruslanshakirov.crm.dto.ProfileResponse;
import com.ruslanshakirov.crm.entity.User;
import com.ruslanshakirov.crm.entity.profile.Profile;
import com.ruslanshakirov.crm.exception.MyBadRequestException;
import com.ruslanshakirov.crm.exception.MyNotFoundException;
import com.ruslanshakirov.crm.mappers.ProfileMapper;
import com.ruslanshakirov.crm.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.ruslanshakirov.crm.exception.ExceptionMessages.NOT_FOUND_ID;
import static com.ruslanshakirov.crm.util.ValidationUtil.checkIdsMatch;
import static com.ruslanshakirov.crm.util.ValidationUtil.checkNotFound;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserService userService;
    private final ProfileMapper profileMapper;

    @Transactional
    public List<ProfileResponse> findByCurrentUser() {
        User user = userService.getCurrentUser();
        return profileRepository.findAllByUser(user).stream()
                .map(profileMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProfileResponse findByIdAndCurrentUser(Long id) {
        Profile profile = getByIdAndCurrentUser(id);
        return profileMapper.toDto(profile);
    }

    public Profile findCurrentProfile() {
        User user = userService.getCurrentUser();
        if (user.getCurrentProfile() == null) {
            throw new MyBadRequestException("У пользователя нет личного профиля. Создайте его.");
        }
        return user.getCurrentProfile();
    }

    @Transactional
    public void removeByIdAndCurrentUser(Long id) {
        Profile profile = getByIdAndCurrentUser(id);
        profileRepository.delete(profile);
    }

    @Transactional
    public ProfileResponse create(Profile profile) {
        User user = userService.getCurrentUser();
        profile.setUser(user);
        return profileMapper.toDto(profileRepository.save(profile));
    }

    @Transactional
    public ProfileResponse update(Profile profile, Long id) {
        checkIdsMatch(profile, id);
        Profile dbProfile = getByIdAndCurrentUser(id);
        profileMapper.toEntity(profile, dbProfile);
        profileRepository.save(dbProfile);
        return profileMapper.toDto(dbProfile);
    }

    @Transactional
    public ProfileResponse changeCurrentProfile(Long profileId) {
        User user = userService.getCurrentUser();
        if (profileId.equals(user.getCurrentProfile().getId())) {
            return profileMapper.toDto(user.getCurrentProfile());
        }
        Profile profile = user.getProfiles().stream()
                .filter(p -> p.getId().equals(profileId))
                .findFirst()
                .orElseThrow(() -> new MyNotFoundException(String.format(NOT_FOUND_ID, "Профиль", profileId)));
        user.setCurrentProfile(profile);
        return profileMapper.toDto(profile);
    }

    private Profile getByIdAndCurrentUser(Long id) {
        User user = userService.getCurrentUser();
        return checkNotFound(profileRepository.findByIdAndUser(id, user), "Профиль", id);
    }
}
