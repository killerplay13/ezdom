package tw.com.cha102.forum.model.dao;

import tw.com.cha102.forum.model.entity.ForumPostVO;

import java.util.List;

public interface ForumPostDao {
    int insert(ForumPostVO forumPostVO);

    int deleteById(Integer forumPostId);

    int updateForumPost(ForumPostVO forumPostVO);

    ForumPostVO selectById(Integer forumPostId);

    List<ForumPostVO> selectAll();
}

