package tw.com.cha102.forum.model.dao;

import tw.com.cha102.forum.model.entity.ForumMsgVO;

import java.util.List;

public interface ForumMsgDao {

    int insert(ForumMsgVO forumMsgVO);

    int deleteById(Integer forumMsgId);

    int updateForumMsg(ForumMsgVO forumMsgVO);

    ForumMsgVO selectById(Integer forumMsgId);

    List<ForumMsgVO> selectAll();
}
