package tw.com.cha102.coachregister.model.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import tw.com.cha102.coachregister.model.dao.CoachMemberDAO;

import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class CoachMemberDAOImpl implements CoachMemberDAO {
    @PersistenceContext
    private Session session;

    @Override
    public void add(CoachMemberDAO coachMemberDAO) {

    }

    @Override
    public void update(CoachMemberDAO coachMemberDAO) {

    }

    @Override
    public void delete(CoachMemberDAO coachMemberDAO) {

    }

    @Override
    public CoachMemberDAO findById(Integer id) {
        return null;
    }

    @Override
    public List<CoachMemberDAO> findAll() {
        return null;
    }
}
