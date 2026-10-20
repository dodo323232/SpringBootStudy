package com.example.springboard.controller;

import com.example.springboard.Util.SessionUtil;
import com.example.springboard.dto.memberDto.LoginDto;
import com.example.springboard.dto.memberDto.MemberResponseDto;
import com.example.springboard.dto.memberDto.SignUpDto;
import com.example.springboard.dto.memberDto.UpdateDto;
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
    private final SessionUtil sessionUtil;

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

    @PutMapping("/update")
    public ResponseEntity<String> memberUpdate(HttpServletRequest request, @RequestBody UpdateDto dto){
        Long memberId = sessionUtil.getLoginMemberId(request);

        service.memberUpdate(memberId, dto.getNickname(), dto.getPassword());

        return ResponseEntity.ok(memberId+"번 정보 갱신 완료.");
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

        Long memberId = sessionUtil.getLoginMemberId(request);

        MemberResponseDto dto = service.info(memberId);

        return ResponseEntity.ok(dto);
    }



    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handlerIllegalState(IllegalStateException e){
        return ResponseEntity.status(401).body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handlerIllegalArgument(IllegalArgumentException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }
}
