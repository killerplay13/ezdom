package tw.com.cha102.forummsg.service;

import tw.com.cha102.forummsg.model.entity.ForumMsgVO;

import java.util.List;

public interface ForumMsgService {

    boolean createMessage(ForumMsgVO forumMsgVO);

    boolean deleteMessage(Integer forumMsgId);


    ForumMsgVO getMessageById(Integer forumMsgId);

    List<ForumMsgVO> getAllMessages();

    List<ForumMsgVO> getMessagesByForumPostId(Integer forumPostId);
}
