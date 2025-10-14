package com.internalfaq.assistant.console;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AdminFlowHandler {

    public void start(Scanner scanner) {
        System.out.println("\n👤 Bienvenido al panel del administrador.\n");

        System.out.print("Por favor, ingresa tu nombre: ");
        String name = scanner.nextLine();

        System.out.println("Hola " + name + ", ¿qué deseas hacer hoy? \n");

        // En próximos subpasos:
        // 1. Pedir nombre del admin
        // 2. Mostrar menú de opciones
        // 3. Ejecutar acciones: ver, editar, aprobar, eliminar
    }
}
