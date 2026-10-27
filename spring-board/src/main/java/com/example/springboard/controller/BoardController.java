package com.example.springboard.controller;

import com.example.springboard.Util.SessionUtil;
import com.example.springboard.dto.boardDto.BoardResponseDto;
import com.example.springboard.dto.boardDto.BoardSaveDto;
import com.example.springboard.dto.boardDto.BoardUpdateDto;
import com.example.springboard.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/read")
    public ResponseEntity<List<BoardResponseDto>> readBoard(
            HttpServletRequest request
    ){
        Long memberId = sessionUtil.getLoginMemberId(request);

        List<BoardResponseDto> List = service.readBoard(memberId);

        return ResponseEntity.ok(List);
    }

    @GetMapping("/board-response")
    public ResponseEntity<List<BoardResponseDto>> responseBoard(){
        return ResponseEntity.ok(service.readAllBoard());
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateBoard(
            @RequestBody BoardUpdateDto dto,
            HttpServletRequest request
            ){
        Long memberId = sessionUtil.getLoginMemberId(request);

        service.updateBoard(memberId,dto);

        return ResponseEntity.ok("게시판 업데이트 완료");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBoard(
            @RequestParam Long boardId,
            HttpServletRequest request){
        Long memberId = sessionUtil.getLoginMemberId(request);

        service.deleteBoard(boardId, memberId);

        return ResponseEntity.ok("삭제되었습니다.");
    }
}
