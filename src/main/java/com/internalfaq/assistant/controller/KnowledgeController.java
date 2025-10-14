package com.internalfaq.assistant.controller;

import com.internalfaq.assistant.model.KnowledgeEntry;
import com.internalfaq.assistant.service.KnowledgeEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/knowledge")
public class KnowledgeController {

    private final KnowledgeEntryService knowledgeEntryService;

    @Autowired
    public KnowledgeController(KnowledgeEntryService knowledgeEntryService) {
        this.knowledgeEntryService = knowledgeEntryService;
    }

    @GetMapping
    public List<KnowledgeEntry> getEntries(@RequestParam(name = "approved") boolean approved) {
        if (approved) {
            return knowledgeEntryService.getApprovedEntries();
        } else {
            return knowledgeEntryService.getPendingEntries();
        }
    }

    @GetMapping("/{id}")
    public KnowledgeEntry getEntryById(@PathVariable Long id) {
        return knowledgeEntryService.getEntryById(id);
    }

    @PostMapping
    public KnowledgeEntry createEntry(@RequestBody KnowledgeEntry newEntry) {
        newEntry.setApproved(false);
        return knowledgeEntryService.createEntry(newEntry);
    }

    @PutMapping("/{id}")
    public KnowledgeEntry updateEntry(
            @PathVariable Long id,
            @RequestBody KnowledgeEntry updatedData) {
        return knowledgeEntryService.updateEntry(id, updatedData);
    }

    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable Long id) {
        knowledgeEntryService.deleteEntry(id);
    }

}
