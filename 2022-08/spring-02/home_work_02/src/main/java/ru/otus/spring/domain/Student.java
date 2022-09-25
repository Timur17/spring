package ru.otus.spring.domain;

import ru.otus.spring.form.Form;

import java.util.List;
import java.util.Scanner;

public class Student implements Person {
    private final String OWN_RESPONSE = "Own response";
    private String firstName;
    private String lastName;
    private Form form;

    public Student(Form form) {
        this.form = form;
    }

    public void askName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Your first name:");
        firstName = sc.nextLine();
        System.out.println("Your last name:");
        lastName = sc.nextLine();
    }

    public void askQuestion() {
        for (int i = 0; i < form.getQuestionnaires().size(); i++) {
            System.out.println("Question " + form.getQuestionnaires().get(i).getId() + ": " + form.getQuestionnaires().get(i).getQuestion());
            List<String> responses = form.getQuestionnaires().get(i).getResponses();
            for (int j = 0; j < responses.size(); j++) {
                System.out.println(j + 1 + ": " + responses.get(j));
            }
            if (responses.size() == 1 && responses.get(0).equals(OWN_RESPONSE)) {
                Scanner ownResponse = new Scanner(System.in);
                form.getQuestionnaires().get(i).setSelectedAnswer(ownResponse.nextLine());
                continue;
            }
            int numberResp = validateAnswer(responses);
            if (responses.get(numberResp - 1).equals(OWN_RESPONSE)) {
                System.out.println("Write own answer.");
                Scanner ownResponse = new Scanner(System.in);
                form.getQuestionnaires().get(i).setSelectedAnswer(ownResponse.nextLine());
            } else
                form.getQuestionnaires().get(i).setSelectedAnswer(responses.get(numberResp - 1));
        }
    }

    public int parseNumber(){
        Scanner sc = new Scanner(System.in);
        Integer numberResp = null;
        while (numberResp == null) {
            try {
                numberResp = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Enter correct number.");
            }
        }
        return numberResp;
    }

    public int validateAnswer(List<String> responses){
        int numberResp = 0;
        while (numberResp < 1 || numberResp > responses.size()) {
            System.out.println("Please choose answer from 1 to " + responses.size());
            numberResp = parseNumber();
        }
        return numberResp;
    }

    public void printResult() {
        System.out.println("Student: " + firstName + " " + lastName + "\n");
        for (int i = 0; i < form.getQuestionnaires().size(); i++) {
            System.out.println("Question " + form.getQuestionnaires().get(i).getId() + ": " + form.getQuestionnaires().get(i).getQuestion());
            System.out.println("Student answer: " + form.getQuestionnaires().get(i).getSelectedAnswer() + "\n");
        }
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
