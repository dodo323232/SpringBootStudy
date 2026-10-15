package com.example.springboard.dto;

import lombok.Data;

@Data
public class SignUpDto {

    private Long memberId;

    private String email;
    private String password;
    private String nickname;

}
