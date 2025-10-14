package com.internalfaq.assistant.service;

import com.internalfaq.assistant.model.KnowledgeEntry;
import java.util.List;

public interface KnowledgeEntryService {

    List<KnowledgeEntry> getApprovedEntries();

    List<KnowledgeEntry> getPendingEntries();

    KnowledgeEntry getEntryById(Long id);

    KnowledgeEntry createEntry(KnowledgeEntry entry);

    KnowledgeEntry updateEntry(Long id, KnowledgeEntry updatedData);

    void deleteEntry(Long id);
}
