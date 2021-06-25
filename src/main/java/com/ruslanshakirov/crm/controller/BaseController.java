package com.ruslanshakirov.crm.controller;

import com.ruslanshakirov.crm.entity.BaseEntity;
import com.ruslanshakirov.crm.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
public abstract class BaseController<T extends BaseEntity, S extends BaseService<T>> {
    private final S service;

    @GetMapping
    public ResponseEntity<List<T>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        service.remove(id);
    }

    @PostMapping
    public ResponseEntity<T> create(T t) {
        T created = service.create(t);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(T t, @PathVariable Long id) {
        return ResponseEntity.ok(service.update(t, id));
    }
}
