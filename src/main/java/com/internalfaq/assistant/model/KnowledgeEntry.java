package com.internalfaq.assistant.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "knowledge_entries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KnowledgeEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String question;

    @Column(nullable = true)
    private String answer;

    @Column(nullable = false)
    private boolean approved = false;  // Nuevo campo: controla si la FAQ está aprobada
}