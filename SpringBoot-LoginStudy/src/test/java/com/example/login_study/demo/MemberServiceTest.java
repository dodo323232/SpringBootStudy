package com.example.login_study.demo;

import com.example.login_study.demo.dto.AuthDto;
import com.example.login_study.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;



@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;   // 진짜 Bean을 주입받음

    @Test
    void signUpTest() {
        AuthDto dto = new AuthDto("test123@naver.com", "1234", "닉네임");

        Long memberId = memberService.signUp(dto);

        // memberId가 null이 아닌지, 제대로 생성됐는지 등을 검증
        assertNotNull(memberId);
    }
}