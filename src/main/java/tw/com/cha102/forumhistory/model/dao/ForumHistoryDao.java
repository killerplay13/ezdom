package tw.com.cha102.forumhistory.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.cha102.forumhistory.model.entity.ForumHistoryVO;

import java.util.List;
@Repository
public interface ForumHistoryDao extends JpaRepository<ForumHistoryVO, Integer> {
    List<ForumHistoryVO> findByMemberId(Integer memberId);
    boolean existsByForumPostIdAndMemberId(Integer forumPostId, Integer memberId);
}
