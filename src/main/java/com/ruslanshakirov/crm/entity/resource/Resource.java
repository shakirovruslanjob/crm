package com.ruslanshakirov.crm.entity.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruslanshakirov.crm.entity.AbstractNamedEntity;
import com.ruslanshakirov.crm.entity.profile.Profile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "resources")
@Getter
@Setter
public class Resource extends AbstractNamedEntity {
    private ResourceType type;
    private String measureUnit;
    private int price;
    private int remainder;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Profile profile;
}
