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

    private final ForumMsgDao forumMsgDao;

    @Autowired
    public ForumMsgServiceImpl(ForumMsgDao forumMsgDao) {
        this.forumMsgDao = forumMsgDao;
    }

    @Override
    public ForumMsgVO createMessage(ForumMsgVO forumMsgVO) {
        ForumMsgVO doMsg = forumMsgDao.save(forumMsgVO);

        if (doMsg != null) {
            doMsg.setSuccessful(true);
            doMsg.setMessage("留言發送成功");
        } else {
            doMsg.setSuccessful(false);
            doMsg.setMessage("留言發送失敗");
        }

        return doMsg;
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
    public boolean saveMessage(ForumMsgVO forumMsgVO) {
        ForumMsgVO savedMsg = forumMsgDao.save(forumMsgVO);
        return savedMsg != null;
    }

    @Override
    public ForumMsgVO getMessageById(Integer forumMsgId) {
        Optional<ForumMsgVO> msgOptional = forumMsgDao.findById(forumMsgId);
        return msgOptional.get();
    }

    @Override
    public List<ForumMsgVO> getAllMessages() {
        return forumMsgDao.findAll();
    }
}