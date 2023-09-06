package tw.com.cha102.forum.service;

import tw.com.cha102.forum.dto.PostDTO;
import tw.com.cha102.forum.model.entity.ForumPostVO;

import java.util.List;

public interface ForumPostService {

    boolean post(ForumPostVO forumPostVO,Integer memberId);

    boolean edit(Integer postId,ForumPostVO forumPostVO);

    List<ForumPostVO> findAll();
    ForumPostVO getPostById(Integer forumPostId);

    boolean delete(Integer forumPostId);



    List<ForumPostVO> findPostsByMemberId(Integer memberId);
    boolean togglePostStatus(Integer postId);

    // 增加文章點擊次數
    boolean incrementClickCount(Integer postId);

    // 查詢熱門文章
    List<ForumPostVO> findPopularPosts();

}
