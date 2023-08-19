package tw.com.cha102.friend.model.dao;

import tw.com.cha102.friend.model.entity.FriendVO;

import java.util.List;

public interface FriendDao {

    public int insert(FriendVO friendVO);
    public int deleteById(Integer friendId);
    public int updateToFriendStatus(FriendVO friendVO);
    public FriendVO selectById(Integer friendId);
    List<FriendVO> selectAll();
}
