package com.example.springboard.mapper;
import com.example.springboard.entity.Member;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface BoardMapper {
    public void save(Member member);

    public Member findByEmail(String email);
    public Member findByNickname(String nickname);
}
