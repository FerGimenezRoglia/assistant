package com.internalfaq.assistant.console;

import com.internalfaq.assistant.model.KnowledgeEntry;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

@Component
public class UserFlowHandler {
    private final RestTemplate restTemplate;

    public UserFlowHandler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void start(Scanner scanner) {
        System.out.println("\n👤 Bienvenido al asistente de empleados.\n");

        System.out.print("Por favor, ingresa tu nombre: ");
        String name = scanner.nextLine();

        System.out.println("Hola " + name + ", encantado de ayudarte.\n");

        while (true) {
            System.out.println("Selecciona una categoría:");
            System.out.println("[1] Calendario y vacaciones");
            System.out.println("[2] Onboarding e integración");
            System.out.println("[3] Tecnología de la empresa");
            System.out.println("[4] Cultura interna");
            System.out.println("[5] Otros");
            System.out.println("[9] Volver al menú principal");
            System.out.println("[0] Salir");
            System.out.print("> ");

            String input = scanner.nextLine();

            switch (input) {
                case "1", "2", "3", "4", "5" -> {
                    // Por ahora no filtramos por categoría real 🦞
                    askQuestionFlow(scanner);
                    return;
                }
                case "9" -> {
                    System.out.println("Volviendo al menú principal...\n");
                    return;
                }
                case "0" -> {
                    System.out.println("Hasta luego.");
                    System.exit(0);
                }
                default -> System.out.println("Opción inválida. Intenta nuevamente.\n");
            }
        }
    }

    private void askQuestionFlow(Scanner scanner) {
        System.out.print("Escribe tu pregunta: ");
        String userQuestion = scanner.nextLine().trim().toLowerCase();

        String url = "http://localhost:8080/api/knowledge?approved=true";

        try {
            KnowledgeEntry[] entries = restTemplate.getForObject(url, KnowledgeEntry[].class);

            KnowledgeEntry match = findMatch(userQuestion, entries);

            if (match != null) {
                System.out.println("\nRespuesta encontrada:");
                System.out.println(match.getAnswer());

                System.out.println("\n¿Esta respuesta fue útil?");
                System.out.println("[1] Sí");
                System.out.println("[2] No");
                System.out.print("> ");
                String option = scanner.nextLine();

                switch (option) {
                    case "1" -> {
                        System.out.println("¡Gracias por tu feedback!");
                        return;
                    }
                    case "2" -> {
                        handleFeedbackOptions(scanner, userQuestion);
                        return;
                    }
                    default -> {
                        System.out.println("Opción inválida. Cerrando.");
                        return;
                    }
                }
            }
            else {
                System.out.println("\nNo encontré una respuesta para esa pregunta.");
                // Próximo subpaso: sugerir o enviar pregunta
            }

        } catch (Exception e) {
            System.out.println("Error al consultar el backend: " + e.getMessage());
        }
    }

    private KnowledgeEntry findMatch(String userQuestion, KnowledgeEntry[] entries) {
        for (KnowledgeEntry entry : entries) {
            String storedQuestion = entry.getQuestion().toLowerCase();
            if (storedQuestion.contains(userQuestion) || userQuestion.contains(storedQuestion)) {
                return entry;
            }
        }
        return null;
    }

    private void handleFeedbackOptions(Scanner scanner, String question) {
        System.out.println("\n¿Querés sugerir una posible respuesta o solo enviar la pregunta?");
        System.out.println("[1] Sugerir respuesta");
        System.out.println("[2] Enviar solo la pregunta");
        System.out.print("> ");
        String option = scanner.nextLine();

        KnowledgeEntry entry = new KnowledgeEntry();
        entry.setQuestion(question);
        entry.setApproved(false);

        if (option.equals("1")) {
            System.out.print("Escribí tu sugerencia: ");
            String answer = scanner.nextLine();
            entry.setAnswer(answer);
        }

        try {
            String url = "http://localhost:8080/api/knowledge";
            restTemplate.postForObject(url, entry, KnowledgeEntry.class);
            System.out.println("\nGracias. Tu sugerencia fue enviada correctamente.");
        } catch (Exception e) {
            System.out.println("Error al enviar la sugerencia: " + e.getMessage());
        }
    }

}
