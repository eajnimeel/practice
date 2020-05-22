package com.example.demo.mail;

import lombok.Builder;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class TemplateEmailServiceTest {

    @Autowired
    TemplateEmailService emailService;

    @Test
    void 메일_전송_테스() throws MessagingException, UnsupportedEncodingException {
        String to = "minjae@bellins.net";
        String subject = "스프링 Mustache 템플릿 메일 테스트";
        String template = "<p>{{name}} 님 안녕하세요.<br>스프링 Mustache 템플릿 메일 테스트입니다.</p>";
        Map<String, Object> context = new HashMap<>();
        context.put("name", "홍길동");

        emailService.sendMessageWithTemplate(to, subject, template, context);

    }

    @Test
    void 트템플릿을_이용한_메일_테스() throws MessagingException, UnsupportedEncodingException {
        String to = "minjae@bellins.net";
        String subject = "스프링 Mustache 템플릿 메일 테스트";
        String template = "<p>{{name}} 님 안녕하세요.<br>스프링 Mustache 템플릿 메일 테스트입니다.<br>{{memberGrade}} 등급으로 승급하셨습니다..</p>";
        MailModel context = MailModel.builder().name("홍길동").memberGrade("VIP").build();

        emailService.sendMessageWithTemplate(to, subject, template, context);
    }

    @Test
    void 같은_메일을_여러_수신자에_보내는_테스트() throws MessagingException, UnsupportedEncodingException {
        String[] to = new String[] { "minjae@bellins.net", "minjae@live.com" };
        String subject = "스프링 Mustache 템플릿 메일 테스트";
        String template = "<p>{{name}} 님 안녕하세요.<br>스프링 Mustache 템플릿 메일 테스트입니다.<br>{{memberGrade}} 등급으로 승급하셨습니다.</p>";
        MailModel context = MailModel.builder().name("홍길동").memberGrade("VIP").build();

        emailService.sendMessageWithTemplate(to, subject, template, context);
    }

    @Test
    void 여러_수신자에_다른내용_보내는_테스트() throws MessagingException, UnsupportedEncodingException {
        String template = "<p>{{name}} 님 안녕하세요.<br>스프링 Mustache 템플릿 메일 테스트입니다.<br>{{memberGrade}} 등급으로 승급하셨습니다..</p>";
        MailModel context1 = MailModel.builder().name("홍길동").memberGrade("VIP").build();
        MailModel context2 = MailModel.builder().name("임꺽정").memberGrade("VVIP").build();

        Mail mail1 = Mail.builder().to("minjae@bellins.net").subject("메일 테스트").context(context1).build();
        Mail mail2 = Mail.builder().to("minjae@live.com").subject("메일 테스트").context(context2).build();

        List<Mail> mailList = Arrays.asList(mail1, mail2);

        emailService.sendMessageWithTemplate(mailList, template);
    }
}

class MailModel {
    @Getter
    private String name;
    @Getter
    private String memberGrade;

    @Builder
    public MailModel(String name, String memberGrade) {
        this.name = name;
        this.memberGrade = memberGrade;
    }
}
