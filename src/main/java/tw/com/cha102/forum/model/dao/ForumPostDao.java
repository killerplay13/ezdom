package tw.com.cha102.forum.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.cha102.forum.model.entity.ForumPostVO;

import java.util.List;

public interface ForumPostDao extends  JpaRepository<ForumPostVO,Integer>{
    List<ForumPostVO> findByMemberId(Integer memberId);//Spring Data JPA根據會員ID查詢相關的文章列表

    List<ForumPostVO> findByMemberIdAndForumPostStatus(Integer memberId, Integer status);

    List<ForumPostVO> findAllByForumPostStatus(Integer status);

    List<ForumPostVO> findByForumPostStatusOrderByForumPostClickCountAsc(Integer status);

}

