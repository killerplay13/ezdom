package tw.com.cha102.forum.service;

import tw.com.cha102.forum.model.entity.ForumMsgVO;

import java.util.List;

public interface ForumMsgService {

    ForumMsgVO createMessage(ForumMsgVO forumMsgVO);

    boolean deleteMessage(Integer forumMsgId);

    boolean saveMessage(ForumMsgVO forumMsgVO);

    ForumMsgVO getMessageById(Integer forumMsgId);

    List<ForumMsgVO> getAllMessages();
}
