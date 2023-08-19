package tw.com.cha102.coach.model.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import tw.com.cha102.coach.model.dao.CoachMessageDAO;
import tw.com.cha102.coach.model.entity.CoachMessageVO;
import tw.com.cha102.order.model.entity.OrderVO;

import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CoachMessageDAOImpl implements CoachMessageDAO {
    @PersistenceContext
    private Session session;
    @Override
    public int add(CoachMessageVO coachMessageVO) {
        session.persist(coachMessageVO);
        return 1;
    }

    @Override
    public int update(CoachMessageVO coachMessageVO) {
        Query query = session.createQuery("UPDATE CoachMessageVO SET " +
                        "content=:content " +
                        "WHERE messageId=:messageId")
                .setParameter("content", coachMessageVO.getContent())
                .setParameter("messageId", coachMessageVO.getMessageId());
        int i = query.executeUpdate();
        return i;
    }

    @Override
    public int delete(Integer id) {
        CoachMessageVO coachMessage = session.load(CoachMessageVO.class, id);
        session.remove(coachMessage);
        return 1;
    }

//    @Override
//    public CoachMessageVO findeById(InternalError id) {
//        return null;
//    }

    @Override
    public List<CoachMessageVO> findAll(Integer coachId) {
        final String hql = "FROM CoachMessageVO WHERE coachId = "
                + coachId
                + " ORDER BY messageId";
        return session
                .createQuery(hql, CoachMessageVO.class)
                .getResultList();
    }
}
