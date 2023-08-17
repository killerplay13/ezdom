package tw.com.cha102.coach.model.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import tw.com.cha102.coach.model.dao.CoachMessageDAO;
import tw.com.cha102.coach.model.entity.CoachMessageVO;

import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CoachMessageDAOImpl implements CoachMessageDAO {
    @PersistenceContext
    private Session session;
    @Override
    public void add(CoachMessageVO coachMessageVO) {

    }

    @Override
    public void update(CoachMessageVO coachMessageVO) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public CoachMessageVO findeById(InternalError id) {
        return null;
    }

    @Override
    public List<CoachMessageVO> findAll() {
        return null;
    }
}
