package com.example.springboard.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public class SessionUtil {
    public Long getLoginMemberId(HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if(session == null) {
            throw new IllegalStateException("로그인이 필요합니다");
        }

        return (Long) session.getAttribute("memberId");
    }
}
