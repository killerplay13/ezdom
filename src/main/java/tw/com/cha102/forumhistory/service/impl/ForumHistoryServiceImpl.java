package tw.com.cha102.forumhistory.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.cha102.forumhistory.model.dao.ForumHistoryDao;
import tw.com.cha102.forumhistory.model.entity.ForumHistoryVO;
import tw.com.cha102.forumhistory.service.ForumHistoryService;

import java.util.List;
@Service
public class ForumHistoryServiceImpl implements ForumHistoryService {
    private final ForumHistoryDao  forumHistoryDao;

    @Autowired
    public ForumHistoryServiceImpl(ForumHistoryDao forumHistoryDao) {

        this.forumHistoryDao = forumHistoryDao;
    }
    @Override
    public boolean history(ForumHistoryVO forumHistoryVO) {
        Integer forumPostId = forumHistoryVO.getForumPostId();
        Integer memberId = forumHistoryVO.getMemberId();

        // 檢查是否已經紀錄過該文章
        if (isPostAlreadyHistory(forumPostId, memberId)) {
            return false; // 已經紀錄過該文章，返回false
        }

        ForumHistoryVO savedHistory = forumHistoryDao.save(forumHistoryVO);

        // 如果保存成功，返回true
        return savedHistory != null;
    }

    @Override
    public List<ForumHistoryVO> findByMemberId(Integer memberId) {

        return forumHistoryDao.findByMemberId(memberId);
    }

    @Override
    public boolean delete(Integer forumHistoryId) {
        if (forumHistoryDao.existsById(forumHistoryId)) {
            forumHistoryDao.deleteById(forumHistoryId);
            return true;
        }
        return false;
    }
    @Override
    public boolean isPostAlreadyHistory(Integer forumPostId, Integer memberId) {
        return forumHistoryDao.existsByForumPostIdAndMemberId(forumPostId, memberId);
    }

}
