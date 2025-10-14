package com.internalfaq.assistant.console;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final ConsoleUI consoleUI;
    private final UserFlowHandler userFlowHandler;
    private final AdminFlowHandler adminFlowHandler;

    public ConsoleRunner(ConsoleUI consoleUI,
                         UserFlowHandler userFlowHandler,
                         AdminFlowHandler adminFlowHandler) {
        this.consoleUI = consoleUI;
        this.userFlowHandler = userFlowHandler;
        this.adminFlowHandler = adminFlowHandler;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        consoleUI.printWelcome();
        int option = consoleUI.askUserType(scanner);

        switch (option) {
            case 1 -> userFlowHandler.start(scanner);
            case 2 -> adminFlowHandler.start(scanner);
            case 0 -> {
                System.out.println("Hasta luego. Saludos!");
                System.exit(0);
            }
            default -> consoleUI.printInvalidOption();
        }

    }
}
