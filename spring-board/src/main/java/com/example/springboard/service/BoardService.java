package com.example.springboard.service;

import com.example.springboard.dto.boardDto.BoardResponseDto;
import com.example.springboard.dto.boardDto.BoardUpdateDto;
import com.example.springboard.entity.Board;
import com.example.springboard.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;
    
    public Long saveBoard(Long memberId, String title, String content){
        Board board = Board.builder()
                .memberId(memberId)
                .title(title)
                .content(content)
                .build();
        boardMapper.save(board);


        return board.getBoardId();
    }

    public List<BoardResponseDto> readBoard(Long memberId){

        return boardMapper.findByMemberId(memberId);
    }

    public void updateBoard(Long memberId, BoardUpdateDto dto){

        boardMapper.updateBoard(dto.getBoardId(), memberId, dto.getTitle(), dto.getContent());
    }

    public List<BoardResponseDto> readAllBoard(){
        return boardMapper.findAllBoard();
    }

    public void deleteBoard(Long boardId, Long memberId){

        boardMapper.deleteBoard(boardId,memberId);
    }
}
