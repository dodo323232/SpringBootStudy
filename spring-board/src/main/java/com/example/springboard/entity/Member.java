package com.example.springboard.entity;


import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
public class Member {

    private Long memberId;

    private String email;
    private String password;
    private String nickname;

    private LocalDateTime createdAt;

}
