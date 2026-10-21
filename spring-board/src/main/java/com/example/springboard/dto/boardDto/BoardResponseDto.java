package com.example.springboard.dto.boardDto;

import lombok.Data;

@Data
public class BoardResponseDto {

    private Long boardId;

    private String title;
    private String content;
    private String nickname;

}
