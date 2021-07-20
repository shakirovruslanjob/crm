package com.ruslanshakirov.crm.service;

import com.ruslanshakirov.crm.entity.AbstractBaseEntity;
import com.ruslanshakirov.crm.exception.MyBadRequestException;
import com.ruslanshakirov.crm.exception.MyNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public abstract class BaseServiceImpl<T extends AbstractBaseEntity, R extends org.springframework.data.jpa.repository.JpaRepository<T, Long>> implements BaseService<T> {
    protected final R repository;

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<T> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public T getById(Long id) {
        return repository.findById(id).orElseThrow(MyNotFoundException::new);
    }

    @Override
    public void remove(Long id) {
        T t = getById(id);
        repository.delete(t);
    }

    @Override
    public T create(T t) {
        if (t.getId() != null) {
            throw new MyBadRequestException("Id must be null");
        }
        return repository.save(t);
    }

    @Override
    public T update(T t, Long id) {
        if (t.getId() == null) {
            t.setId(id);
        } else if (!t.getId().equals(id)) {
            throw new MyBadRequestException(String.format("Id in object doesn't match id in request %d!=%d", t.getId(), id));
        }
        return repository.save(t);
    }
}
