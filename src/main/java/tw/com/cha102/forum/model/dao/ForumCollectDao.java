package tw.com.cha102.forum.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tw.com.cha102.forum.model.entity.ForumCollectVO;

import java.util.List;

@Repository
public interface ForumCollectDao extends JpaRepository<ForumCollectVO, Integer> {
    boolean existsByForumPostId(Integer forumPostId);//Spring Data JPA會根據文章ID檢查是否存在相應的文章,並返回一個布林值,表示是否存在。
    List<ForumCollectVO> findByMemberId(Integer memberId);//Spring Data JPA會根據會員ID查詢相關的文章收藏列表

//    List<ForumCollectVO> findByForumPostId(Integer forumPostId);

}
