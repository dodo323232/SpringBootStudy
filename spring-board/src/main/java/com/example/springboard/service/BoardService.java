package com.example.springboard.service;

import com.example.springboard.entity.Board;
import com.example.springboard.entity.Member;
import com.example.springboard.mapper.BoardMapper;
import com.example.springboard.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
