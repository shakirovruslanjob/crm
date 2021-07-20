package com.ruslanshakirov.crm;

import com.ruslanshakirov.crm.dto.UserResponse;
import com.ruslanshakirov.crm.entity.User;
import com.ruslanshakirov.crm.mappers.UserMapper;
import lombok.Getter;


public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    @Getter
    private final UserResponse user;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
        this.user = UserMapper.INSTANCE.toDto(user);
    }
}
