package tw.com.cha102.forum.model.dao;

import tw.com.cha102.forum.model.entity.ForumCategoryVO;

import java.util.List;

public interface ForumCategoryDao {

    int insert(ForumCategoryVO forumCategoryVO);

    int deleteById(Integer forumCategoryId);

    int updateForumCategory(ForumCategoryVO forumCategoryVO);

    ForumCategoryVO selectById(Integer forumCategoryId);

    List<ForumCategoryVO> selectAll();
}

