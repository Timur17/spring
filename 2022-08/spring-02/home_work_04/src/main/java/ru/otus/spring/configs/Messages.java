package ru.otus.spring.configs;

import org.springframework.stereotype.Component;

@Component
public class Messages {
    private String ownResponse;
    private String msgWrite;
    private String msgQuestion;
    private String msgAnswer;
    private String msgChooseAnswer;
    private String msgYourFirstName;
    private String msgYourLastName;
    private String msgStudent;
    private String msgEnterCorrectNumber;
    private String msgIdIsNotInt;

    public String getOwnResponse() {
        return ownResponse;
    }

    public void setOwnResponse(String ownResponse) {
        this.ownResponse = ownResponse;
    }

    public String getMsgWrite() {
        return msgWrite;
    }

    public void setMsgWrite(String msgWrite) {
        this.msgWrite = msgWrite;
    }

    public String getMsgQuestion() {
        return msgQuestion;
    }

    public void setMsgQuestion(String msgQuestion) {
        this.msgQuestion = msgQuestion;
    }

    public String getMsgAnswer() {
        return msgAnswer;
    }

    public void setMsgAnswer(String msgAnswer) {
        this.msgAnswer = msgAnswer;
    }

    public String getMsgChooseAnswer() {
        return msgChooseAnswer;
    }

    public void setMsgChooseAnswer(String msgChooseAnswer) {
        this.msgChooseAnswer = msgChooseAnswer;
    }

    public String getMsgYourFirstName() {
        return msgYourFirstName;
    }

    public void setMsgYourFirstName(String msgYourFirstName) {
        this.msgYourFirstName = msgYourFirstName;
    }

    public String getMsgYourLastName() {
        return msgYourLastName;
    }

    public void setMsgYourLastName(String msgYourLastName) {
        this.msgYourLastName = msgYourLastName;
    }

    public String getMsgStudent() {
        return msgStudent;
    }

    public void setMsgStudent(String msgStudent) {
        this.msgStudent = msgStudent;
    }

    public String getMsgEnterCorrectNumber() {
        return msgEnterCorrectNumber;
    }

    public void setMsgEnterCorrectNumber(String msgEnterCorrectNumber) {
        this.msgEnterCorrectNumber = msgEnterCorrectNumber;
    }

    public String getMsgIdIsNotInt() {
        return msgIdIsNotInt;
    }

    public void setMsgIdIsNotInt(String msgIdIsNotInt) {
        this.msgIdIsNotInt = msgIdIsNotInt;
    }

    @Override
    public String toString() {
        return "Messages{" +
                "ownResponse='" + ownResponse + '\'' +
                ", msgWrite='" + msgWrite + '\'' +
                ", msgQuestion='" + msgQuestion + '\'' +
                ", msgAnswer='" + msgAnswer + '\'' +
                ", msgChooseAnswer='" + msgChooseAnswer + '\'' +
                ", msgYourFirstName='" + msgYourFirstName + '\'' +
                ", msgYourLastName='" + msgYourLastName + '\'' +
                ", msgStudent='" + msgStudent + '\'' +
                ", msgEnterCorrectNumber='" + msgEnterCorrectNumber + '\'' +
                ", msgIdIsNotInt='" + msgIdIsNotInt + '\'' +
                '}';
    }
}
