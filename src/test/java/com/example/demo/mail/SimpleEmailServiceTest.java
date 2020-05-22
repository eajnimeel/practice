package com.example.demo.mail;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SimpleEmailServiceTest {

    @Autowired
    SimpleEmailService simpleEmailService;

    @Test
    void sendSimpleMessage() {

        String to = "minjae@bellins.net";
        String subject = "스프링 메일 테스트";
        String text = "스프링 메일 테스트 시작";

        simpleEmailService.sendSimpleMessage(to, subject, text);
    }
}
