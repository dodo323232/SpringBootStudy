package com.example.springboard.controller;

import com.example.springboard.dto.LoginDto;
import com.example.springboard.dto.MemberResponseDto;
import com.example.springboard.dto.SignUpDto;
import com.example.springboard.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    @PostMapping("/signup")
    public ResponseEntity<String> signUP(@RequestBody SignUpDto signUpDto){
        Long createId = service.signUp(signUpDto);

        return ResponseEntity.ok(createId+"번 계정이 생성되었습니다.");
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody LoginDto loginDto,
            HttpServletRequest request
    ){

        Long memberId = service.login(loginDto);

        HttpSession session = request.getSession();
        session.setAttribute("memberId",memberId);

        return ResponseEntity.ok(memberId+"번 로그인 완료."+session);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request){

        HttpSession session = request.getSession(false);

        if(session == null) {
            throw new IllegalStateException("로그인이 필요합니다");
        }

        session.invalidate();

        return ResponseEntity.ok("로그아웃 성공");
    }

    @GetMapping("/info")
    public ResponseEntity<MemberResponseDto> memberInfo(HttpServletRequest request){

        Long memberId = getLoginMemberId(request);

        MemberResponseDto dto = service.info(memberId);

        return ResponseEntity.ok(dto);
    }

    private Long getLoginMemberId(HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if(session == null) {
            throw new IllegalStateException("로그인이 필요합니다");
        }

        return (Long) session.getAttribute("memberId");
    }
}
