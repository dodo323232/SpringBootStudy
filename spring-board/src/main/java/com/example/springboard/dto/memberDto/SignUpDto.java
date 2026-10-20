package com.example.springboard.dto.memberDto;

import lombok.Data;

@Data
public class SignUpDto {

    private Long memberId;

    private String email;
    private String password;
    private String nickname;

}
