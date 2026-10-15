package com.example.springboard.controller;

import com.example.springboard.dto.LoginDto;
import com.example.springboard.dto.SignUpDto;
import com.example.springboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final MemberService service;

    @PostMapping("/signup")
    public ResponseEntity<String> signUP(@RequestBody SignUpDto signUpDto){
        Long createId = service.signUp(signUpDto);

        return ResponseEntity.ok(createId+"번 계정이 생성되었습니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        Long createId = service.login(loginDto);

        return ResponseEntity.ok(createId+"번 로그인 완료.");
    }
}
