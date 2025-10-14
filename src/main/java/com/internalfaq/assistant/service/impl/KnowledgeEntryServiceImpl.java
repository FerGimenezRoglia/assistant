package com.internalfaq.assistant.service.impl;

import com.internalfaq.assistant.exception.custom.KnowledgeEntryNotFoundException;
import com.internalfaq.assistant.model.KnowledgeEntry;
import com.internalfaq.assistant.repository.KnowledgeEntryRepository;
import com.internalfaq.assistant.service.KnowledgeEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgeEntryServiceImpl implements KnowledgeEntryService {

    private final KnowledgeEntryRepository repository;

    @Autowired
    public KnowledgeEntryServiceImpl(KnowledgeEntryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<KnowledgeEntry> getApprovedEntries() {
        return repository.findByApprovedTrue();
    }

    @Override
    public List<KnowledgeEntry> getPendingEntries() {
        return repository.findByApprovedFalse();
    }

    @Override
    public KnowledgeEntry getEntryById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new KnowledgeEntryNotFoundException(id));
    }

    @Override
    public KnowledgeEntry createEntry(KnowledgeEntry entry) {
        return repository.save(entry);
    }

    @Override
    public KnowledgeEntry updateEntry(Long id, KnowledgeEntry updatedEntry) {
        KnowledgeEntry existingEntry = repository.findById(id)
                .orElseThrow(() -> new KnowledgeEntryNotFoundException(id));

        if (updatedEntry.getQuestion() != null) {
            existingEntry.setQuestion(updatedEntry.getQuestion());
        }

        if (updatedEntry.getAnswer() != null) {
            existingEntry.setAnswer(updatedEntry.getAnswer());
        }

        existingEntry.setApproved(updatedEntry.isApproved());

        return repository.save(existingEntry);
    }

    @Override
    public void deleteEntry(Long id) {
        KnowledgeEntry existingEntry = repository.findById(id)
                .orElseThrow(() -> new KnowledgeEntryNotFoundException(id));

        repository.delete(existingEntry);
    }
}
