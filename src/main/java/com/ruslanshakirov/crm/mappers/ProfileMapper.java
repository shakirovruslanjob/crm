package com.ruslanshakirov.crm.mappers;

import com.ruslanshakirov.crm.dto.AgentProfileResponse;
import com.ruslanshakirov.crm.dto.ProfileResponse;
import com.ruslanshakirov.crm.entity.profile.Agent;
import com.ruslanshakirov.crm.entity.profile.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileResponse toDto(Profile profile);

    List<ProfileResponse> toDto(List<Profile> profiles);

    Agent toEntity(AgentProfileResponse counterAgentProfile);

    void toEntity(AgentProfileResponse profileDto, @MappingTarget Agent profile);

    void toEntity(Profile profile, @MappingTarget Profile dbProfile);

}
