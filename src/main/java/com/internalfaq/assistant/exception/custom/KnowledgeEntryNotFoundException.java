package com.internalfaq.assistant.exception.custom;

public class KnowledgeEntryNotFoundException extends RuntimeException {
    public KnowledgeEntryNotFoundException(Long id) {
        super("Knowledge entry with ID " + id + " was not found.");
    }
}
