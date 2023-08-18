package tw.com.cha102.friend.model.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import tw.com.cha102.friend.model.dao.FriendDao;
import tw.com.cha102.friend.model.entity.FriendVO;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class FriendDaoImpl implements FriendDao {

    @PersistenceContext
    private Session session;

    @Override
    public int insert(FriendVO friendVO) {
        session.persist(friendVO);
        return 1;
    }

    @Override
    public int deleteById(Integer friendId) {
        FriendVO friend = session.get(FriendVO.class, friendId);
        session.remove(friend);
        return 1;
    }

    @Override
    public int updateToFriendStatus(FriendVO friendVO) {
        Query query = session.createQuery("UPDATE FriendVO SET friendStatus=:friendStatus where friendId=:friendId")
                .setParameter("FriendStatus", friendVO.getFriendStatus()).setParameter("friendId", friendVO.getFriendId());
        int updateCount = query.executeUpdate();
        return updateCount;
    }

    @Override
    public FriendVO selectById(Integer friendId) {
        return session.get(FriendVO.class, friendId);
    }

    @Override
    public List<FriendVO> selectAll() {
        final String hql = "FROM FriendVO ORDER BY id";
        return session.createQuery(hql, FriendVO.class).getResultList();
    }
}
