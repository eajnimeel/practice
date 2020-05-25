package com.example.demo.validation;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
public class Member {

    @Builder
    public Member(@NotEmpty(groups = UpdateGroup.class) Integer memberNo,
            @NotEmpty @Length(min = 6, max = 20) String loginId,
            @NotEmpty @Length(min = 2, max = 64) String memberNm, @NotEmpty @Email String email,
            @NotEmpty String mobile, String tel) {
        this.memberNo = memberNo;
        this.loginId = loginId;
        this.memberNm = memberNm;
        this.email = email;
        this.mobile = mobile;
        this.tel = tel;
    }

    @NotNull(groups = UpdateGroup.class)
    @Min(0)
    @Max(Long.MAX_VALUE)
    private Integer memberNo;

    @NotEmpty
    @Length(min = 6, max = 20)
    private String loginId;

    @NotEmpty
    @Length(min = 2, max = 64)
    private String memberNm;

    @NotEmpty
    @Email
    @Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    private String email;

    @NotEmpty
    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3,4})[.-]?(\\d{4})$")
    private String mobile;

    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3,4})[.-]?(\\d{4})$")
    private String tel;
}
