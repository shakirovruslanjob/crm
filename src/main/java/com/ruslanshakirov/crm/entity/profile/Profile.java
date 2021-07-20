package com.ruslanshakirov.crm.entity.profile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruslanshakirov.crm.entity.AbstractNamedEntity;
import com.ruslanshakirov.crm.entity.User;
import com.ruslanshakirov.crm.entity.resource.Resource;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "profiles")
@Getter
@Setter
@Accessors(chain = true)
public class Profile extends AbstractNamedEntity {
    private String bin;
    private String subsidiary = "Основное подразделение";
    private String warehouse = "Основной склад";
    private String manager;
    private String accountant = "Не предусмотрен";
    private String reason;
    private Integer kbe;
    private String vatCertificate;
    private Integer vatFare;
    private VatType vatType = VatType.NONE;
    private int startNum = 1;
    @Embedded
    private Address address;
    @Embedded
    private BankAccount bankAccount;
    @ManyToOne
    @JsonIgnore
    private User user;
    @OneToMany(mappedBy = "profile")
    private List<Resource> resources;
    @ElementCollection
    @CollectionTable(name = "bank_accounts", joinColumns = @JoinColumn(name = "profile_id"))
    private List<BankAccount> bankAccounts;
}
