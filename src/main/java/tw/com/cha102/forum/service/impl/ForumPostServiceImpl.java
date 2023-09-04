package tw.com.cha102.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.cha102.forum.dto.PostDTO;
import tw.com.cha102.forum.model.entity.ForumPostVO;
import tw.com.cha102.forum.model.dao.ForumPostDao;
import tw.com.cha102.forum.service.ForumPostService;

import java.util.List;
import java.util.Optional;

@Service
public class ForumPostServiceImpl implements ForumPostService {

    @Autowired
    private ForumPostDao forumPostDao;

    //發布新文章
    @Override
    public ForumPostVO post(PostDTO postDTO,Integer memberId) {  // 修改方法參數名稱和返回類型
        ForumPostVO forumPostVO = new ForumPostVO();
        forumPostVO.setForumPostContent(postDTO.getForumPostContent());
        forumPostVO.setForumPostTitle(postDTO.getForumPostTitle());
        forumPostVO.setForumPostType(postDTO.getForumPostType());
        forumPostVO.setMemberId(memberId);

        return forumPostDao.save(forumPostVO);


    }

    //編輯指定文章
    @Override
    public ForumPostVO edit(Integer postId, PostDTO postDTO) {  // 修改方法參數名稱
        Optional<ForumPostVO> existingPostOptional = forumPostDao.findById(postId);

        if (existingPostOptional.isPresent()) {
            ForumPostVO existingPost = existingPostOptional.get();
            existingPost.setForumPostTitle(postDTO.getForumPostTitle());
            existingPost.setForumPostContent(postDTO.getForumPostContent());
            existingPost.setForumPostType(postDTO.getForumPostType());

            // 其他邏輯 - 處理相關業務邏輯

            ForumPostVO updatedPost = forumPostDao.save(existingPost);
            // 根據需要執行其他處理

            return updatedPost;
        }

        return null;
    }

    //列出所有文章
    @Override
    public List<ForumPostVO> findAll() {
        return forumPostDao.findAll();
    }

    //刪除指定文章
    @Override
    public boolean delete(Integer forumPostId) {
        Optional<ForumPostVO> postOptional = forumPostDao.findById(forumPostId);

        if (postOptional.isPresent()) {
            forumPostDao.delete(postOptional.get());
            return true;
        }

        return false;
    }

    //儲存文章
    @Override
    public boolean save(ForumPostVO forumPostVO) {
        ForumPostVO savedPost = forumPostDao.save(forumPostVO);

        if (savedPost != null) {
            forumPostVO.setMessage("儲存成功");
            forumPostVO.setSuccessful(true);
            return true;
        } else {
            return false;
        }
    }

    //取得指定ID的文章
    @Override
    public ForumPostVO getPostById(Integer forumPostId) {
        Optional<ForumPostVO> postOptional = forumPostDao.findById(forumPostId);
        return postOptional.get();
    }

    //列出指定會員ID的文章
    @Override
    public List<ForumPostVO> findPostsByMemberId(Integer memberId) {

        return forumPostDao.findByMemberId(memberId);

    }


}
