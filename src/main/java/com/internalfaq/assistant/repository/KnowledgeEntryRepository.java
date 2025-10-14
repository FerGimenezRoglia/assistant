package com.internalfaq.assistant.repository;

import com.internalfaq.assistant.model.KnowledgeEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface KnowledgeEntryRepository extends JpaRepository<KnowledgeEntry, Long> {
    List<KnowledgeEntry> findByApprovedTrue();
    List<KnowledgeEntry> findByApprovedFalse();
}