package com.example.login_study.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateRequestDto {
    private String password;
    private String nickname;
}
