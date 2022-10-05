package ru.otus.spring.services;

public interface ApplicationStopService {
    boolean isApplicationRunning();
    void stopApplication();
}
