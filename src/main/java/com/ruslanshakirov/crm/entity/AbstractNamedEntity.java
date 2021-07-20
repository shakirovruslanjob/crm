package com.ruslanshakirov.crm.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractNamedEntity extends AbstractBaseEntity {
    protected String name;
}
