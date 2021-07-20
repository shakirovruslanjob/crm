package com.ruslanshakirov.crm.entity;

public interface Persistable<ID> {
    boolean isNew();

    void setId(ID id);

    ID getId();
}
