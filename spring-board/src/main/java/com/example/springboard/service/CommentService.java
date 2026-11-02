package com.example.springboard.service;

import com.example.springboard.dto.commentDto.CommentResponseDto;
import com.example.springboard.dto.commentDto.CommentSaveDto;
import com.example.springboard.dto.commentDto.CommentUpdateDto;
import com.example.springboard.entity.Comment;
import com.example.springboard.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

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

        commentMapper.commentSave(comment);
    }

    public List<CommentResponseDto> readComment(Long memberId){
        return commentMapper.commentRead(memberId);
    }

    public void updateComment(
            Long commentId,
            Long memberId,
            Long boardId,
            CommentUpdateDto dto
    ){

        if(commentId == null){
            throw new IllegalArgumentException("commentId 없음");
        }
        if(boardId == null){
            throw new IllegalArgumentException("boardId 없음");
        }
        commentMapper.commentUpdate(memberId, boardId, commentId, dto.getContent());


    }


}
