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

    public boolean createMessage(ForumMsgVO forumMsgVO) {
        try {
            forumMsgDao.save(forumMsgVO);
            return true; // 如果保存成功，返回true
        } catch (Exception e) {
            return false; // 如果保存出現異常，返回false
        }
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
    public ForumMsgVO getMessageById(Integer forumMsgId) {
        Optional<ForumMsgVO> msgOptional = forumMsgDao.findById(forumMsgId);
        return msgOptional.get();
    }

    @Override
    public List<ForumMsgVO> getAllMessages() {
        return forumMsgDao.findAll();
    }
    @Override
    public List<ForumMsgVO> getMessagesByForumPostId(Integer forumPostId) {
        List<ForumMsgVO> messages = forumMsgDao.findByForumPostId(forumPostId);

        // 設置 memberName
        for (ForumMsgVO msg : messages) {
            msg.setMemberName(msg.getMember().getMemberName());
//            msg.setMemberPhoto(msg.getMember().getMemberPhoto());
        }

        return forumMsgDao.findByForumPostId(forumPostId);
    }
}
