package tw.com.cha102.forum.model.dao;

import tw.com.cha102.forum.model.entity.ForumCollectVO;

import java.util.List;

public interface ForumCollectDao {

    int insert(ForumCollectVO forumCollectVO);

    int deleteById(Integer forumCollectId);

    int updateForumCollect(ForumCollectVO forumCollectVO);

    ForumCollectVO selectById(Integer forumCollectId);

    List<ForumCollectVO> selectAll();
}
