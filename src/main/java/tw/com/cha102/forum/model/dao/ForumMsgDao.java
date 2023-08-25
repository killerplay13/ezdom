package tw.com.cha102.forum.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tw.com.cha102.forum.model.entity.ForumCollectVO;
import tw.com.cha102.forum.model.entity.ForumMsgVO;

import java.util.List;

@Repository
public interface ForumMsgDao extends JpaRepository<ForumMsgVO, Integer> {

//    List<ForumMsgVO> findByForumPostId(Integer forumPostId);
}
