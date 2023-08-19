package tw.com.cha102.member.model.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import tw.com.cha102.member.model.dao.MemberDao;
import tw.com.cha102.member.model.entity.MemberVO;

import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberDaoImpl implements MemberDao {

    @PersistenceContext
    private Session session;

    @Override
    public int insert(MemberVO memberVO) {
        session.persist(memberVO);
        return 1;
    }

    @Override
    public int update(MemberVO memberVO) {
        session.update(memberVO);
        return 1;
    }

    @Override
    public int deleteById(Integer memberId) {
        MemberVO member = session.get(MemberVO.class, memberId);
        if (member != null) {
            session.delete(member);
            return 1;
        }
        return 0;
    }

    @Override
    public MemberVO selectById(Integer memberId) {
        return session.get(MemberVO.class, memberId);
    }

    @Override
    public List<MemberVO> selectAll() {
        final String hql = "FROM MemberVO ORDER BY id";
        return session.createQuery(hql, MemberVO.class).getResultList();
    }
}
