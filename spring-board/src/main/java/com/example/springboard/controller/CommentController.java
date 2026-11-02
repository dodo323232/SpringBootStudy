package com.example.springboard.controller;

import com.example.springboard.Util.SessionUtil;
import com.example.springboard.dto.commentDto.CommentResponseDto;
import com.example.springboard.dto.commentDto.CommentSaveDto;
import com.example.springboard.dto.commentDto.CommentUpdateDto;
import com.example.springboard.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final SessionUtil sessionUtil;
    private final CommentService commentService;

    @PostMapping("/{boardId}/save")
    public ResponseEntity<String> saveComment(
            @PathVariable Long boardId,
            @RequestBody CommentSaveDto dto,
            HttpServletRequest request
            ){
        Long memberId = sessionUtil.getLoginMemberId(request);

        commentService.saveComment(memberId,boardId,dto);

        return ResponseEntity.ok("댓글 작성 성공");
    }

    @GetMapping("/read")
    public ResponseEntity<List<CommentResponseDto>> readComment(
            HttpServletRequest request
    ){
        Long memberId = sessionUtil.getLoginMemberId(request);

        List<CommentResponseDto> dtoList = commentService.readComment(memberId);

        return ResponseEntity.ok(dtoList);
    }

    @PutMapping("/{boardId}/{commentId}")
    public ResponseEntity<String> updateComment(
            @PathVariable Long boardId,
            @PathVariable Long commentId,
            @RequestBody CommentUpdateDto dto,
            HttpServletRequest request
            ){
        Long memberId = sessionUtil.getLoginMemberId(request);

        commentService.updateComment(commentId, memberId, boardId, dto);

        return ResponseEntity.ok("댓글 수정 완료!");
    }
}
