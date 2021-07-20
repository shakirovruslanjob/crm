package com.ruslanshakirov.crm.dto;

import com.ruslanshakirov.crm.entity.profile.Address;
import com.ruslanshakirov.crm.entity.profile.BankAccount;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProfileDto extends BaseDto {
    private String bin;
    private String name;
    private Address address;
    private BankAccount bankAccount;
    private List<BankAccount> bankAccounts;
}
