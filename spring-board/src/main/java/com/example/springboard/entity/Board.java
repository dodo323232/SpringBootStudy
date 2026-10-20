package com.example.springboard.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Board {

    private Long boardId;
    private Long memberId;

    private String title;
    private String content;

    private LocalDateTime createdAt;
}
