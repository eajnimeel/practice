package com.example.demo.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/validation/member")
    public String createMember(@Validated(CreateGroup.class) Member member) {

        return memberService.member(member);
    }

    @PutMapping("/validation/member")
    public String updateMember(@Validated(UpdateGroup.class) Member member) {
        return memberService.member(member);
    }
}
