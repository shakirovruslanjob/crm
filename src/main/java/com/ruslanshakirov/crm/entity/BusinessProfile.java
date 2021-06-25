package com.ruslanshakirov.crm.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "business_profiles")
public class BusinessProfile extends BaseEntity {
    private String name;
    private String subsidiary;
    private String manager;
    private String accountant;
    private String bin;
    private String realAddress;
    private String legalAddress;
    private String email;
    private String phone;
    private String site;
    @OneToMany(mappedBy = "businessProfile", cascade = CascadeType.ALL)
    List<BankAccount> bankAccounts;
}
