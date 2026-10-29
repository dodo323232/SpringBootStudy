package com.example.springboard.service;

import com.example.springboard.dto.commentDto.CommentSaveDto;
import com.example.springboard.entity.Comment;
import com.example.springboard.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentMapper commentMapper;

    public void saveComment(
            Long memberId,
            Long boardId,
            CommentSaveDto dto
    ){
        Comment comment = Comment.builder()
                .memberId(memberId)
                .boardId(boardId)
                .content(dto.getContent())
                .build();

        commentMapper.save(comment);
    }

}
