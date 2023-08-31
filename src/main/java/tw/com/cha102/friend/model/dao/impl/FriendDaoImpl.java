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

    @Override
    public int insert(FriendVO friendVO) {
        return 0;
    }

    @Override
    public int deleteById(Integer friendId) {
        return 0;
    }

    @Override
    public int updateToFriendStatus(FriendVO friendVO) {
        return 0;
    }

    @Override
    public FriendVO selectById(Integer friendId) {
        return null;
    }

    @Override
    public List<FriendVO> selectAll() {
        return null;
    }
}
