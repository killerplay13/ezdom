package tw.com.cha102.forummsg.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.cha102.forummsg.model.entity.ForumMsgVO;

import java.util.List;

@Repository
public interface ForumMsgDao extends JpaRepository<ForumMsgVO, Integer> {

    List<ForumMsgVO> findByForumPostId(Integer forumPostId);
    List<ForumMsgVO> findByMemberId(Integer memberId);
}
