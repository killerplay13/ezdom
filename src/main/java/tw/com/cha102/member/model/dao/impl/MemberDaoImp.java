package tw.com.cha102.member.model.dao.impl;

import org.springframework.stereotype.Repository;
import tw.com.cha102.member.model.dao.MemberDao;
import tw.com.cha102.member.model.entity.Member;

import java.util.List;
@Repository
public class MemberDaoImp implements MemberDao {
    @Override
    public int insert(Member memberVO) {
        return 0;
    }

    @Override
    public int update(Member memberVO) {
        return 0;
    }

    @Override
    public int deleteById(Integer memberId) {
        return 0;
    }

    @Override
    public Member selectById(Integer memberId) {
        return null;
    }

    @Override
    public List<Member> selectAll() {
        return null;
    }
}
