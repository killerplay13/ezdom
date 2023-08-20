package tw.com.cha102.member.model.dao;

import tw.com.cha102.member.model.entity.Member;

import java.util.List;

public interface MemberDao {
    public int insert(Member memberVO);
    public int update(Member memberVO);
    public int deleteById(Integer memberId);
    public Member selectById(Integer memberId);
    List<Member> selectAll();

}
