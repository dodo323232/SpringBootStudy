package com.example.login_study.demo.entity;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    private Long memberId;

    // 회원 정보
    private String email;
    private String password;
    private String nickname;


    private LocalDateTime createdAt;
}