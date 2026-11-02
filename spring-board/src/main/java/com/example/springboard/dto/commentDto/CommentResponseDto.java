package com.example.springboard.dto.commentDto;

import lombok.Data;

@Data
public class CommentResponseDto {
    private String boardTitle;
    private String boardContent;
    private String commentContent;
}
