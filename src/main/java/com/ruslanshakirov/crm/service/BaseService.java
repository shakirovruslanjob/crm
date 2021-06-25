package com.ruslanshakirov.crm.service;

import com.ruslanshakirov.crm.entity.BaseEntity;
import com.ruslanshakirov.crm.repository.BaseRepository;

import java.util.List;

public interface BaseService<T extends BaseEntity> {
    List<T> getAll();

    T getById(Long id);

    void remove(Long id);

    T create(T t);

    T update(T t, Long id);
}
