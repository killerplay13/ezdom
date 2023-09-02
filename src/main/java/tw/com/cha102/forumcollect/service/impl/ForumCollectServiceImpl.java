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
        Integer forumPostId = forumCollectVO.getForumPostId();
        Integer memberId = forumCollectVO.getMemberId();
        // 檢查是否已經收藏過該文章
        if (isPostAlreadyCollected(forumPostId, memberId)) {
            forumCollectVO.setSuccessful(false);
            forumCollectVO.setMessage("收藏過該文章");
            return forumCollectVO;
        }

        ForumCollectVO savedCollect = forumCollectDao.save(forumCollectVO);

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

    public boolean isPostAlreadyCollected(Integer forumPostId, Integer memberId) {
        return forumCollectDao.existsByForumPostIdAndMemberId(forumPostId, memberId);
    }


    @Override
    public List<ForumCollectVO> findByMemberId(Integer memberId) {

        return forumCollectDao.findByMemberId(memberId);
    }

}
