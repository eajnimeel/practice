package com.example.demo.mail;

import lombok.Builder;
import lombok.Getter;

public class Mail {

    @Getter
    private String to;
    @Getter
    private String subject;
    @Getter
    private Object context;
    @Getter
    private String from;
    @Getter
    private String personal;

    @Builder
    private Mail(String to, String subject, Object context, String from, String personal) {
        this.to = to;
        this.subject = subject;
        this.context = context;
        this.from = from;
        this.personal = personal;
    }
}
