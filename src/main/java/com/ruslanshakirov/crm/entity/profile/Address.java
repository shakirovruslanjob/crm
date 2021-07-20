package com.ruslanshakirov.crm.entity.profile;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@Accessors(chain = true)
public class Address {
    @Column(name = "address_real")
    private String real;
    @Column(name = "address_legal")
    private String legal;
    private String email;
    private String phone;
    private String site;
}
