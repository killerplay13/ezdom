package tw.com.cha102.forum.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.cha102.forum.model.entity.ForumPostVO;

import java.util.List;

public interface ForumPostDao extends  JpaRepository<ForumPostVO,Integer>{
    List<ForumPostVO> findByMemberId(Integer memberId);//Spring Data JPA 會根據會員 ID 查詢相關的文章列表
}

