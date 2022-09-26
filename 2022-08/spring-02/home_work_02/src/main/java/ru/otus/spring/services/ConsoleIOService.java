package ru.otus.spring.services;

import java.util.Scanner;

public class ConsoleIOService {
    private final Scanner userInput;

    public ConsoleIOService() {
        userInput = new Scanner(System.in);
    }

    public void outputString(String msg) {
        System.out.println(msg);
    }

    public int readInt() {
        return Integer.parseInt(userInput.nextLine());
    }

    public String readString() {
        return userInput.nextLine();
    }

    public int readIntWithPrompt(String prompt){
        outputString(prompt);
        return Integer.parseInt(userInput.nextLine());
    }

    public String readStringWithPrompt(String prompt){
        outputString(prompt);
        return userInput.nextLine();
    }
}
