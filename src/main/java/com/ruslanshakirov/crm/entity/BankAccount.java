package com.ruslanshakirov.crm.entity;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bank_accounts")
public class BankAccount extends BaseEntity {
    private String bank;
    private String swift;
    private String iban;
    @ManyToOne
    private BusinessProfile businessProfile;
}
