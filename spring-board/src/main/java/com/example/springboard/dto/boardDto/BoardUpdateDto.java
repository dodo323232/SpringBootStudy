package com.example.springboard.dto.boardDto;

import lombok.Data;

@Data
public class BoardUpdateDto {
    private Long boardId;
    private String title;
    private String content;
}
