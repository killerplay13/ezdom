package tw.com.cha102.groupcreate.model;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class GroupCreateDAOImpl implements GroupCreateDAO{

    @PersistenceContext
    private Session session;
    @Override
    public void insert(GroupCreateVO groupCreateVO) {
    session.persist(groupCreateVO);
    }

//    @Override
//    public void update(GroupCreateVO groupCreateVO) {
//
//    }
//
//    @Override
//    public List<GroupCreateVO> getAll() {
//        return null;
//    }
}
