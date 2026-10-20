package com.example.springboard.mapper;

import com.example.springboard.entity.Board;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
       public void save(Board board);
}
