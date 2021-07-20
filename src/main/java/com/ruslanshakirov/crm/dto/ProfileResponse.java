package com.ruslanshakirov.crm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileResponse extends ProfileDto {
    private String subsidiary;
    private String manager;
}
