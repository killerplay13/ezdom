package tw.com.cha102.forumcollect.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.cha102.forumcollect.model.entity.ForumCollectVO;

import java.util.List;

@Repository
public interface ForumCollectDao extends JpaRepository<ForumCollectVO, Integer> {
    boolean existsByForumPostIdAndMemberId(Integer forumPostId, Integer memberId);////判斷會員是否已經對特定文章進行過收藏
    List<ForumCollectVO> findByMemberId(Integer memberId);//根據會員ID查詢相關的文章收藏列表

}
