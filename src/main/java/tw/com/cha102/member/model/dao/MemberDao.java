package tw.com.cha102.member.model.dao;

import tw.com.cha102.member.model.entity.MemberVO;

import java.util.List;

public interface MemberDao {
    public int insert(MemberVO memberVO);
    public int update(MemberVO memberVO);
    public int deleteById(Integer memberId);
    public MemberVO selectById(Integer memberId);
    List<MemberVO> selectAll();

}
