package com.example.springboard.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Comment {
    private Long commentId;
    private Long boardId;
    private Long memberId;

    private String content;

    private LocalDateTime createdAt;
}
