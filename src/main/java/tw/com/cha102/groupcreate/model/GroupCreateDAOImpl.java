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
    public int insert(GroupCreateVO groupCreateVO) {
    session.persist(groupCreateVO);
    return 1;
    }

    @Override
    public int update(GroupCreateVO groupCreateVO) {
    return 1;
    }
//
//    @Override
//    public List<GroupCreateVO> getAll() {
//        return null;
//    }
}
