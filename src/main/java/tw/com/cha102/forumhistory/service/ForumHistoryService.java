package tw.com.cha102.forumhistory.service;

import tw.com.cha102.forumhistory.model.entity.ForumHistoryVO;


import java.util.List;

public interface ForumHistoryService {

    List<ForumHistoryVO> findByMemberId(Integer memberId);

    boolean history(ForumHistoryVO forumHistoryVO);

    boolean isPostAlreadyHistory(Integer forumPostId,Integer memberId);

    boolean delete(Integer forumHistoryId);
}
