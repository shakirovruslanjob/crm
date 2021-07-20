package com.ruslanshakirov.crm.dto;

import com.ruslanshakirov.crm.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private boolean enabled;
    private List<ProfileResponse> profiles;
    private Long currentProfileId;
    private Set<Role> roles;
}
