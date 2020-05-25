package com.example.demo.validation;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class MemberService {

    public String member(@Valid Member member) {

        return member.getLoginId();
    }
}
