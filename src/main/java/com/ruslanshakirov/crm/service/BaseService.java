package com.ruslanshakirov.crm.service;

import com.ruslanshakirov.crm.entity.AbstractBaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseService<T extends AbstractBaseEntity> {
    List<T> getAll();

    Page<T> getAll(Pageable pageable);

    T getById(Long id);

    void remove(Long id);

    T create(T t);

    T update(T t, Long id);
    

}
