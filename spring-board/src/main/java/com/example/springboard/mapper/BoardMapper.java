package com.example.springboard.mapper;

import com.example.springboard.dto.boardDto.BoardResponseDto;
import com.example.springboard.entity.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
       public void save(Board board);

       public void updateBoard(
               @Param("boardId") Long boardId,
               @Param("memberId") Long memberId,
               @Param("title")String title,
               @Param("content")String content);

       public void deleteBoard(Long boardId, Long memberId);

       public List<BoardResponseDto> findByMemberId(Long memberId);

       public List<BoardResponseDto> findAllBoard();

}
