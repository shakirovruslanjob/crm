package com.ruslanshakirov.crm.entity.profile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruslanshakirov.crm.entity.AbstractNamedEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "agent_profiles")
@Getter
@Setter
public class Agent extends AbstractNamedEntity {
    private String bin;
    @Enumerated(value = EnumType.STRING)
    private AgentType agentType;
    @Enumerated(value = EnumType.STRING)
    private LegalType legalType;
    private String position;
    private LocalDate birthDate;
    private String passport;
    private String comment;
    @Embedded
    private Address address;
    @Embedded
    private BankAccount bankAccount;
    @ElementCollection
    @CollectionTable(name = "bank_accounts", joinColumns = @JoinColumn(name = "agent_profile_id"))
    private List<BankAccount> bankAccounts;
    @OneToOne
    @JsonIgnore
    private Profile profile;
}

