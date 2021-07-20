package com.ruslanshakirov.crm.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruslanshakirov.crm.entity.Persistable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseDto implements Persistable<Long> {
    protected Long id;

    @Override
    @JsonIgnore
    public boolean isNew() {
        return id == null;
    }
}
