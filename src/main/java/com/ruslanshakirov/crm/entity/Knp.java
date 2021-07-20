package com.ruslanshakirov.crm.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Knp extends AbstractBaseEntity {
    private String name;
    private String description;
}
