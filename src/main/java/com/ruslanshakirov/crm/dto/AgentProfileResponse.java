package com.ruslanshakirov.crm.dto;

import com.ruslanshakirov.crm.entity.profile.AgentType;
import com.ruslanshakirov.crm.entity.profile.LegalType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AgentProfileResponse extends ProfileDto {
    private AgentType agentType;
    private LegalType legalType;
    private String position;
    private LocalDate birthDate;
    private String passport;
    private String comment;
    private String realAddress;
    private String legalAddress;
    private String email;
    private String phone;
    private String site;
}
