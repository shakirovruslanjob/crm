package com.ruslanshakirov.crm.controller;

import com.ruslanshakirov.crm.entity.document.Doc;
import com.ruslanshakirov.crm.entity.document.DocStatus;
import com.ruslanshakirov.crm.service.DocService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class DocController {
    private final DocService docService;

    @GetMapping
    public List<Doc> filter(@RequestParam(required = false) DocStatus status,
                            @RequestParam(required = false) LocalDate startDate,
                            @RequestParam(required = false) LocalDate endDate,
                            @RequestParam(required = false) String agentName) {
        return docService.filter(status, startDate, endDate, agentName);
    }

    @PostMapping
    public Doc create(Doc doc) {
        return docService.create(doc);
    }

    @PutMapping("/{id}")
    public Doc update(@PathVariable Long id, Doc doc) {
        return docService.update(doc, id);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        docService.delete(id);
    }
}
