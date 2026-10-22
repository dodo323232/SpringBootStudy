package com.example.springboard.service;

import com.example.springboard.dto.memberDto.MemberResponseDto;
import com.example.springboard.dto.memberDto.LoginDto;
import com.example.springboard.dto.memberDto.SignUpDto;
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

        validateSignUp(signUpDto);

        String encodedPassword = passwordEncoder.encode(signUpDto.getPassword());

        Member member = Member.builder()
                .email(signUpDto.getEmail())
                .password(encodedPassword)
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

    public MemberResponseDto info(Long memberId){
        Member member = mapper.findById(memberId);

        return new MemberResponseDto(member);
    }

    public void memberUpdate(Long memberId, String newNick, String newPassword){

        String encodedPassword = passwordEncoder.encode(newPassword);

        mapper.memberUpdate(memberId, newNick, encodedPassword);
    }



    private void validateSignUp(SignUpDto signUpDto){
        validateLength(signUpDto.getEmail(), 8, "이메일");
        validateLength(signUpDto.getPassword(), 8, "비밀번호");

        if(mapper.findByEmail(signUpDto.getEmail()) != null){
            throw new IllegalArgumentException("이미 가입된 이메일입니다");
        }

        if(mapper.findByNickname(signUpDto.getNickname()) != null){
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }
    }

    private void validateLength(String value, int min, String fieldName){
        if(value == null || value.length() < min){
            throw new IllegalArgumentException(fieldName+"은(는) "+min+"글자 이상이어야 합니다.");
        }
    }

}
