package tw.com.cha102.forummsg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.cha102.forummsg.model.dao.ForumMsgDao;
import tw.com.cha102.forummsg.model.entity.ForumMsgVO;
import tw.com.cha102.forummsg.service.ForumMsgService;

import java.util.List;
import java.util.Optional;

@Service
public class ForumMsgServiceImpl implements ForumMsgService {
    @Autowired
    private ForumMsgDao forumMsgDao;


    public boolean createMessage(ForumMsgVO forumMsgVO) {
        ForumMsgVO savedMsg = forumMsgDao.save(forumMsgVO);
        return savedMsg != null;
    }


    @Override
    public boolean deleteMessage(Integer forumMsgId) {
        Optional<ForumMsgVO> existingMsgOptional = forumMsgDao.findById(forumMsgId);
        if (!existingMsgOptional.isPresent()) {
            return false;
        }

        forumMsgDao.deleteById(forumMsgId);
        return true;
    }


    @Override
    public List<ForumMsgVO> getMessagesByForumPostId(Integer forumPostId) {
        List<ForumMsgVO> messages = forumMsgDao.findByForumPostId(forumPostId);
        return forumMsgDao.findByForumPostId(forumPostId);
    }

}
