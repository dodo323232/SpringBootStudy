package com.example.springboard.mapper;

import com.example.springboard.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
    public void save(Comment comment);
}
