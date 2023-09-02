package tw.com.cha102.forum.service;

import tw.com.cha102.forum.dto.PostDTO;
import tw.com.cha102.forum.model.entity.ForumPostVO;

import java.util.List;

public interface ForumPostService {

    ForumPostVO post(PostDTO postDTO,Integer memberId);

    ForumPostVO edit(Integer postId,PostDTO postDTO);

    List<ForumPostVO> findAll();
    ForumPostVO getPostById(Integer forumPostId);


    boolean delete(Integer forumPostId);

    boolean save(ForumPostVO forumPostVO);


    List<ForumPostVO> findPostsByMemberId(Integer memberId);

}
