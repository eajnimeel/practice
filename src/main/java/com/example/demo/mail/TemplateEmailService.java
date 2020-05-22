package com.example.demo.mail;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TemplateEmailService {

    /**
     * 스프링에서 제공하는 메일 전송 유틸 클래스
     */
    private final JavaMailSender mailSender;

    /**
     * Mustache 템플릿 엔진 컴파일러
     */
    private final Mustache.Compiler mustacheCompiler;

    /**
     * 한명에게 메일 전송
     * @param to 받는 사람 메일 주소
     * @param subject 메일 제목
     * @param template 메일 Mustache 템플릿
     * @param context 템플릿에 매핑할 내용
     * @throws MessagingException
     */
    public void sendMessageWithTemplate(String to, String subject, String template, Object context)
            throws MessagingException, UnsupportedEncodingException {
        sendMessageWithTemplate(new String[] {to}, subject, template, context);
    }

    /**
     * 동일 메일 내용을 복수의 수신자에게 전송
     *  @param to 받는 사람 메일 주소
     *  @param subject 메일 제목
     *  @param template 메일 Mustache 템플릿
     *  @param context 템플릿에 매핑할 내용
     * @throws MessagingException
     */
    public void sendMessageWithTemplate(String[] to, String subject, String template, Object context)
            throws MessagingException, UnsupportedEncodingException {
        Template t = mustacheCompiler.compile(template);
        String text = t.execute(context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        helper.setFrom("info@esbop.com", "이스밥");

        mailSender.send(message);
    }

    /**
     * 동일 템플릿을 다른 매핑 내용으로 복수의 수신자에게 전송
     * @param mailList
     * @param template
     * @throws MessagingException
     */
    public void sendMessageWithTemplate(List<Mail> mailList, String template)
            throws MessagingException, UnsupportedEncodingException {
        Template t = mustacheCompiler.compile(template);

        for (Mail mail : mailList) {
            String text = t.execute(mail.getContext());

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            helper.setText(text, true);
            helper.setFrom(mail.getFrom(), mail.getPersonal());

            mailSender.send(message);
        }
    }
}
