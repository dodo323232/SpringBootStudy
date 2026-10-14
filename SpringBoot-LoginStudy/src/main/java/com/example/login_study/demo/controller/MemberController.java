package com.example.login_study.demo.controller;

import com.example.login_study.demo.dto.AuthDto;
import com.example.login_study.demo.dto.LoginDto;
import com.example.login_study.demo.dto.MemberResponseDto;
import com.example.login_study.demo.dto.UpdateRequestDto;
import com.example.login_study.demo.jwt.JwtUtil;
import com.example.login_study.demo.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final JwtUtil jwtUtil;
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody AuthDto authDto){
        Long createId = memberService.signUp(authDto);

        return ResponseEntity.ok(createId+"번 계정 생성 완료!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpServletRequest request){

        Long memberId = memberService.login(loginDto);

        HttpSession session = request.getSession();
        session.setAttribute("memberId",memberId);

        return ResponseEntity.ok("로그인 성공");
    }

    @PostMapping("/jwt-login")
    public ResponseEntity<String> jwtLogin(@RequestBody LoginDto loginDto){

        Long memberId = memberService.login(loginDto);

        String token = jwtUtil.createToken(memberId);

        return ResponseEntity.ok(token);
    }

    @GetMapping("/check")
    public ResponseEntity<String> check(HttpServletRequest request){

        Long memberId = getLoginMemberId(request);

        return ResponseEntity.ok("현재 세션" + memberId);
    }

    @GetMapping("/info")
    public ResponseEntity<MemberResponseDto> info(HttpServletRequest request){
        Long memberId = getLoginMemberId(request);

        return ResponseEntity.ok(memberService.info(memberId));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request){
        getValidSession(request).invalidate();

        return ResponseEntity.ok("로그아웃 성공");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(HttpServletRequest request){

        Long memberId = getLoginMemberId(request);
        getValidSession(request).invalidate();

        memberService.delete(memberId);

        return ResponseEntity.ok(memberId+"번 삭제 완료");
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(
            @RequestBody UpdateRequestDto dto,
            HttpServletRequest request
    ){
        Long memberId = getLoginMemberId(request);
        memberService.update(memberId, dto.getNickname(), dto.getPassword());

        return ResponseEntity.ok(memberId+"번 업데이트 완료");
    }




    private Long getLoginMemberId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("memberId") == null) {
            throw new IllegalStateException("로그인이 필요합니다");
        }
        return (Long) session.getAttribute("memberId");
    }

    private HttpSession getValidSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("memberId") == null) {
            throw new IllegalStateException("로그인이 필요합니다");
        }
        return session;
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalState(IllegalStateException e) {
        return ResponseEntity.status(401).body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }
}
