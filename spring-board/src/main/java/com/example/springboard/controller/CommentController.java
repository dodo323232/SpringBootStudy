package com.example.springboard.controller;

import com.example.springboard.Util.SessionUtil;
import com.example.springboard.dto.commentDto.CommentSaveDto;
import com.example.springboard.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final SessionUtil sessionUtil;
    private final CommentService commentService;

    @PostMapping("/{boardId}")
    public ResponseEntity<String> saveComment(
            @PathVariable Long boardId,
            @RequestBody CommentSaveDto dto,
            HttpServletRequest request
            ){
        Long memberId = sessionUtil.getLoginMemberId(request);

        commentService.saveComment(memberId,boardId,dto);

        return ResponseEntity.ok("댓글 작성 성공");
    }
}
