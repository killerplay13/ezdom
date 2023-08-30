package tw.com.cha102.forumcollect.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.cha102.forumcollect.model.entity.ForumCollectVO;
import tw.com.cha102.forumcollect.model.dao.ForumCollectDao;
import tw.com.cha102.forumcollect.service.ForumCollectService;

import java.util.List;

@Service
public class ForumCollectServiceImpl implements ForumCollectService {

    private final ForumCollectDao forumCollectDao;

    @Autowired
    public ForumCollectServiceImpl(ForumCollectDao forumCollectDao) {
        this.forumCollectDao = forumCollectDao;
    }

    @Override
    public ForumCollectVO collect(ForumCollectVO forumCollectVO) {
        // 檢查是否已經收藏過該文章
        if (isPostAlreadyCollected(forumCollectVO.getForumPostId())) {
            forumCollectVO.setSuccessful(false);
            forumCollectVO.setMessage("已經收藏過該文章了");
            return forumCollectVO;
        }

        ForumCollectVO savedCollect = forumCollectDao.save(forumCollectVO);

        if (savedCollect != null) {
            savedCollect.setSuccessful(true);
            savedCollect.setMessage("收藏成功");
        } else {
            savedCollect.setSuccessful(false);
            savedCollect.setMessage("收藏失敗");
        }

        return savedCollect;
    }

    @Override
    public List<ForumCollectVO> findAll() {
        return forumCollectDao.findAll();
    }

    @Override
    public ForumCollectVO getCollectById(Integer forumCollectId) {
        return forumCollectDao.findById(forumCollectId).orElse(null);
    }

    @Override
    public boolean delete(Integer forumCollectId) {
        if (forumCollectDao.existsById(forumCollectId)) {
            forumCollectDao.deleteById(forumCollectId);
            return true;
        }
        return false;
    }

    @Override
    public boolean save(ForumCollectVO forumCollectVO) {
        return forumCollectDao.save(forumCollectVO) != null;
    }

    private boolean isPostAlreadyCollected(Integer forumPostId) {
        // 根據 forumPostId 查詢是否已經存在收藏記錄
        return forumCollectDao.existsByForumPostId(forumPostId);
    }

    @Override
    public List<ForumCollectVO> findByMemberId(Integer memberId) {
        return forumCollectDao.findByMemberId(memberId);
    }

}
