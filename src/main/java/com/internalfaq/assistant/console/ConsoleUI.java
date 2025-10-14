package com.internalfaq.assistant.console;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleUI {

    public void printWelcome() {
        System.out.println("===================================");
        System.out.println("  Bienvenido al Asistente Interno  ");
        System.out.println("===================================");
    }

    public int askUserType(Scanner scanner) {
        System.out.println("¿Quién eres?");
        System.out.println("[1] Empleado");
        System.out.println("[2] Administrador");
        System.out.println("[0] Salir");
        System.out.print("> ");
        String input = scanner.nextLine();

        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }


    public void printInvalidOption() {
        System.out.println("Opción inválida. Finalizando programa.");
    }
}
