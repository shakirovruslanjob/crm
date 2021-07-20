package com.ruslanshakirov.crm.controller;

import com.ruslanshakirov.crm.entity.resource.Resource;
import com.ruslanshakirov.crm.entity.resource.ResourceType;
import com.ruslanshakirov.crm.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/resources")
@RequiredArgsConstructor
public class ResourceController {
    private final ResourceService resourceService;

    @GetMapping
    public Page<Resource> findByType(@RequestParam ResourceType type,
                                     @PageableDefault(sort = "name") Pageable pageable) {
        return resourceService.findByType(type, pageable);
    }

    @GetMapping("/{id}")
    public Resource findById(@PathVariable Long id) {
        return resourceService.findById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        resourceService.remove(id);
    }

    @PostMapping
    public ResponseEntity<Resource> create(Resource resource) {
        Resource created = resourceService.create(resource);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{id}")
    public Resource update(@PathVariable Long id, Resource resource) {
        return resourceService.update(resource, id);
    }
}
