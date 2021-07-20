package com.ruslanshakirov.crm.mappers;

import com.ruslanshakirov.crm.dto.UserRequest;
import com.ruslanshakirov.crm.dto.UserResponse;
import com.ruslanshakirov.crm.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "currentProfileId", source = "user.currentProfile.id")
    UserResponse toDto(User user);

    User toEntity(UserRequest user);
}
