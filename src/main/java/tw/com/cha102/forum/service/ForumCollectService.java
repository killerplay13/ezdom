package tw.com.cha102.forum.service;

import tw.com.cha102.forum.model.entity.ForumCollectVO;

import java.util.List;

public interface ForumCollectService {

    ForumCollectVO collect(ForumCollectVO forumCollectVO);

    List<ForumCollectVO> findAll();

    List<ForumCollectVO> findByMemberId(Integer memberId);

    ForumCollectVO getCollectById(Integer forumCollectId);

    boolean delete(Integer forumCollectId);

    boolean save(ForumCollectVO forumCollectVO);
}
