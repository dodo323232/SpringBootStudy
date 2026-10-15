package com.example.springboard.service;

import com.example.springboard.dto.LoginDto;
import com.example.springboard.dto.SignUpDto;
import com.example.springboard.entity.Member;
import com.example.springboard.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberMapper mapper;

    public Long signUp(SignUpDto signUpDto){

        if(mapper.findByEmail(signUpDto.getEmail()) != null){
            throw new IllegalArgumentException("이미 가입된 이메일입니다");
        }

        if(mapper.findByNickname(signUpDto.getNickname()) != null){
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }

        String encodedPaswword = passwordEncoder.encode(signUpDto.getPassword());

        Member member = Member.builder()
                .email(signUpDto.getEmail())
                .password(encodedPaswword)
                .nickname(signUpDto.getNickname())
                .build();

        mapper.save(member);

        return member.getMemberId();
    }

    public Long login(LoginDto dto){
        if(mapper.findByEmail(dto.getEmail()) == null){
            throw new IllegalArgumentException("이메일이나 비밀번호가 틀렸습니다.");
        }

        Member member = mapper.findByEmail(dto.getEmail());

        String encodedPassword = member.getPassword();
        boolean matchedPassword = passwordEncoder.matches(dto.getPassword(), encodedPassword);

        if(!matchedPassword){
            throw new IllegalArgumentException("이메일이나 비밀번호가 틀렸습니다.");
        }

        return member.getMemberId();
    }




}
