package ru.otus.spring.services;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.spring.configs.AppProps;


@Component
public class LocalQuestionnaireFile {
    private final MessageSource messageSource;
    private final AppProps props;

    public LocalQuestionnaireFile(MessageSource messageSource, AppProps props) {
        this.messageSource = messageSource;
        this.props = props;
    }

    public void init() {
        var messageLocalized = messageSource.getMessage("file.src", null, props.getLocale());
        props.setFile(messageLocalized);
        var messageLocalized2 = messageSource.getMessage("msg.own.response", null, props.getLocale());
        props.getMessages().setOwnResponse(messageLocalized2);
        var messageLocalized3 = messageSource.getMessage("msg.write", null, props.getLocale());
        props.getMessages().setMsgWrite(messageLocalized3);
        var messageLocalized4 = messageSource.getMessage("msg.question", null, props.getLocale());
        props.getMessages().setMsgQuestion(messageLocalized4);
        var messageLocalized5 = messageSource.getMessage("msg.choose.answer", null, props.getLocale());
        props.getMessages().setMsgChooseAnswer(messageLocalized5);
        var messageLocalized6 = messageSource.getMessage("msg.answer", null, props.getLocale());
        props.getMessages().setMsgAnswer(messageLocalized6);
        var messageLocalized7 = messageSource.getMessage("msg.your.first.name", null, props.getLocale());
        props.getMessages().setMsgYourFirstName(messageLocalized7);
        var messageLocalized8 = messageSource.getMessage("msg.your.last.name", null, props.getLocale());
        props.getMessages().setMsgYourLastName(messageLocalized8);
        var messageLocalized9 = messageSource.getMessage("msg.student", null, props.getLocale());
        props.getMessages().setMsgStudent(messageLocalized9);
        System.out.println("Prop after init:  " + props);
    }
}
