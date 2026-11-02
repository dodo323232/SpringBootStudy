package com.example.springboard.mapper;

import com.example.springboard.dto.commentDto.CommentResponseDto;
import com.example.springboard.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    public void commentSave(Comment comment);
    public void commentUpdate(Long memberId, Long boardId, Long commentId, String content);
    public List<CommentResponseDto> commentRead(Long memberId);
}
