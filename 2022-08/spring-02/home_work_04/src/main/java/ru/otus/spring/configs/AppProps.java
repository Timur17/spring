package ru.otus.spring.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@ConfigurationProperties(prefix = "application")
public class AppProps {
    private String file;
    private Locale locale;
    private Messages messages;

    public AppProps(Messages messages) {
        this.messages = messages;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Messages getMessages() {
        return messages;
    }

    public void setMessages(Messages messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "AppProps{" +
                "file='" + file + '\'' +
                ", locale=" + locale +
                ", messages=" + messages +
                '}';
    }
}
