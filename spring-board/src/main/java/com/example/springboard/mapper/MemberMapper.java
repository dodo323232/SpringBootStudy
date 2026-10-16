package com.example.springboard.mapper;
import com.example.springboard.entity.Member;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface MemberMapper {
    public void save(Member member);

    public Member findByEmail(String email);
    public Member findByNickname(String nickname);
    public Member findById(Long memberId);
}
