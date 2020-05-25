package com.example.demo.validation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    void member1() {
        Member member = Member.builder()
                .build();

        assertThrows(ConstraintViolationException.class, () -> memberService.member(member));
    }

    @Test
    void member2() {
        Member member = Member.builder()
                .loginId("minjae")
                .memberNm("이민재")
                .email("minjae@live.com")
                .mobile("01095668006")
                .build();

        assertThat(memberService.member(member)).isEqualTo("minjae");
    }
}
