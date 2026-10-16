package com.example.springboard.dto;

import com.example.springboard.entity.Member;
import lombok.Data;

@Data
public class MemberResponseDto {

    private String email;
    private String nickname;

    public MemberResponseDto(Member member){
        this.email = member.getEmail();
        this.nickname = member.getNickname();
    }
}
