package ru.otus.spring.domain;

public interface Person {


    public String getFirstName();

    public String getLastName();

    public void setFirstName(String firstName);

    public void setLastName(String lastName);

    public void askName();

    public void askQuestion();

    public void printResult();
}
