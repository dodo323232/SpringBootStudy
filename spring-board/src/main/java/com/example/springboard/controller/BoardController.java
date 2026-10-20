package com.example.springboard.controller;

import com.example.springboard.Util.SessionUtil;
import com.example.springboard.dto.boardDto.BoardSaveDto;
import com.example.springboard.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

    private final BoardService service;
    private final SessionUtil sessionUtil;

    @PostMapping("/save")
    public ResponseEntity<String> saveBoard(
            @RequestBody BoardSaveDto dto,
            HttpServletRequest request
    ){
        Long memberId =  sessionUtil.getLoginMemberId(request);

        Long boardId = service.saveBoard(memberId, dto.getTitle(), dto.getContent());

        return ResponseEntity.ok(boardId+"번 게시물 생성");
    }
}
