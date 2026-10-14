package com.example.login_study.demo.mapper;


import com.example.login_study.demo.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {

    public void save(Member member);

    public Member findByEmail(String email);
    public Member findByNickname(String nickname);
    public Member findByid(Long memberId);
    public void deleteByMemberId(Long memberId);
    public void update(
            @Param("memberId") Long memberId,
            @Param("nickname") String nickname,
            @Param("password") String password
    );
}
