package tw.com.cha102.forumcollect.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.cha102.forumcollect.model.entity.ForumCollectVO;
import tw.com.cha102.forumcollect.model.dao.ForumCollectDao;
import tw.com.cha102.forumcollect.service.ForumCollectService;

import java.util.List;

@Service
public class ForumCollectServiceImpl implements ForumCollectService {
    @Autowired
    private ForumCollectDao forumCollectDao;


    @Override
    public boolean collect(ForumCollectVO forumCollectVO) {
        Integer forumPostId = forumCollectVO.getForumPostId();
        Integer memberId = forumCollectVO.getMemberId();
        if (isPostAlreadyCollected(forumPostId, memberId)) { //檢查是否已經收藏過該文章
            return false;
        }
        ForumCollectVO savedCollect = forumCollectDao.save(forumCollectVO);
        return savedCollect != null;
    }


    @Override
    public boolean delete(Integer forumCollectId) {
        if (forumCollectDao.existsById(forumCollectId)) {
            forumCollectDao.deleteById(forumCollectId);
            return true;
        }
        return false;
    }

    public boolean isPostAlreadyCollected(Integer forumPostId, Integer memberId) {
        return forumCollectDao.existsByForumPostIdAndMemberId(forumPostId, memberId);
    }


    @Override
    public List<ForumCollectVO> findByMemberId(Integer memberId) {

        return forumCollectDao.findByMemberId(memberId);
    }

}
