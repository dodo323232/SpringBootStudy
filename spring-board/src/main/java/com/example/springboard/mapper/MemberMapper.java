package com.example.springboard.mapper;
import com.example.springboard.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface MemberMapper {
    public void save(Member member);

    public void memberUpdate(
            @Param("memberId") Long memberId,
            @Param("nickname") String nickname,
            @Param("password") String password);

    public Member findByEmail(String email);
    public Member findByNickname(String nickname);
    public Member findById(Long memberId);

}
