package com.ruslanshakirov.crm.entity.profile;


import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "iban")
public class BankAccount {
    private String iban;
    private String swift;
    private String bank;
}
