package tw.com.cha102.forumcollect.service;

import tw.com.cha102.forumcollect.model.entity.ForumCollectVO;

import java.util.List;

public interface ForumCollectService {

    boolean collect(ForumCollectVO forumCollectVO);

    List<ForumCollectVO> findAll();

    List<ForumCollectVO> findByMemberId(Integer memberId);

    ForumCollectVO getCollectById(Integer forumCollectId);

    boolean delete(Integer forumCollectId);


    boolean isPostAlreadyCollected(Integer forumPostId,Integer memberId);
}
