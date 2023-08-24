package tw.com.cha102.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.cha102.forum.model.dao.ForumMsgDao;
import tw.com.cha102.forum.model.entity.ForumMsgVO;
import tw.com.cha102.forum.service.ForumMsgService;

import java.util.List;

@Service
public class ForumMsgServiceImpl implements ForumMsgService {

    private final ForumMsgDao forumMsgDao;

    @Autowired
    public ForumMsgServiceImpl(ForumMsgDao forumMsgDao) {
        this.forumMsgDao = forumMsgDao;
    }

    @Override
    public ForumMsgVO createMessage(ForumMsgVO forumMsgVO) {
        ForumMsgVO savedMsg = forumMsgDao.save(forumMsgVO);

        if (savedMsg != null) {
            savedMsg.setSuccessful(true);
            savedMsg.setMessage("留言發送成功");
        } else {
            forumMsgVO.setSuccessful(false);
            forumMsgVO.setMessage("留言發送失敗");
        }

        return savedMsg;
    }

    @Override
    public boolean deleteMessage(Integer forumMsgId) {
        if (forumMsgDao.existsById(forumMsgId)) {
            forumMsgDao.deleteById(forumMsgId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean saveMessage(ForumMsgVO forumMsgVO) {
        ForumMsgVO savedMsg = forumMsgDao.save(forumMsgVO);
        return savedMsg != null;
    }

    @Override
    public ForumMsgVO getMessageById(Integer forumMsgId) {
        ForumMsgVO msg = forumMsgDao.findById(forumMsgId).orElse(null);
        return msg;
    }

    @Override
    public List<ForumMsgVO> getAllMessages() {
        return forumMsgDao.findAll();
    }
}
