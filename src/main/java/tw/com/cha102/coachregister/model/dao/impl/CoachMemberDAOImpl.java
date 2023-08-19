package tw.com.cha102.coachregister.model.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import tw.com.cha102.coach.model.entity.CoachMessageVO;
import tw.com.cha102.coachregister.model.dao.CoachMemberDAO;
import tw.com.cha102.coachregister.model.entity.CoachMemberVO;
import tw.com.cha102.order.model.entity.OrderVO;

import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class CoachMemberDAOImpl implements CoachMemberDAO {
    @PersistenceContext
    private Session session;

    @Override
    public int add(CoachMemberVO coachMemberVO) {
        session.persist(coachMemberVO);
        return 1;
    }

    @Override
    public int update(CoachMemberVO coachMemberVO) {
        Query query = session
                .createQuery("UPDATE CoachMemberVO SET " +
                        "introduction=:introduction, " +
                        "picture=:picture, " +
                        "status=:status " +
                        "WHERE memberId=:memberId")
                .setParameter("introduction", coachMemberVO.getIntroduction())
                .setParameter("picture", coachMemberVO.getPicture())
                .setParameter("status", coachMemberVO.getStatus())
                .setParameter("memberId", coachMemberVO.getMemberId());
        int i = query.executeUpdate();
        return i;
    }

    @Override
    public int delete(Integer id) {
        CoachMemberVO coachMember = session.load(CoachMemberVO.class, id);
        session.remove(coachMember);
        return 1;
    }

    @Override
    public CoachMemberVO findById(Integer id) {
        return session.get(CoachMemberVO.class, id);
    }

    @Override
    public List<CoachMemberVO> findAll() {
        final String hql = "FROM CoachMemberVO ORDER BY memberId";
        return session
                .createQuery(hql, CoachMemberVO.class)
                .getResultList();
    }
}
