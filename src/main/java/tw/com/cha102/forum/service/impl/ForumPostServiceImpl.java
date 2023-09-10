package tw.com.cha102.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.cha102.forum.model.entity.ForumPostVO;
import tw.com.cha102.forum.model.dao.ForumPostDao;
import tw.com.cha102.forum.service.ForumPostService;

import java.util.List;
import java.util.Optional;

@Service
public class ForumPostServiceImpl implements ForumPostService {

    @Autowired
    private ForumPostDao forumPostDao;


    @Override
    public boolean post(ForumPostVO forumPostVO) {

        ForumPostVO savedPost = forumPostDao.save(forumPostVO);
        // 檢查savedPost是否為null來判斷保存是否成功
        return savedPost != null;
    }

    // 編輯指定文章
    @Override
    public boolean edit(Integer postId, ForumPostVO forumPostVO) {
        Optional<ForumPostVO> existingPostOptional = forumPostDao.findById(postId);

        if (existingPostOptional.isPresent()) {
            ForumPostVO existingPost = existingPostOptional.get();//針對文章標題內容狀態進行編輯
            existingPost.setForumPostTitle(forumPostVO.getForumPostTitle());
            existingPost.setForumPostContent(forumPostVO.getForumPostContent());
            existingPost.setForumPostType(forumPostVO.getForumPostType());

            ForumPostVO updatedPost = forumPostDao.save(existingPost);

            return updatedPost != null; //檢查updatedPost是否為null來判斷編輯是否成功
        }

        return false;
    }



    @Override
    public boolean delete(Integer forumPostId) {
        Optional<ForumPostVO> postOptional = forumPostDao.findById(forumPostId);

        if (postOptional.isPresent()) {
            forumPostDao.delete(postOptional.get());
            return true;
        }

        return false;
    }


    @Override
    public List<ForumPostVO> findAll() {

        return forumPostDao.findAllByForumPostStatus(0);
    }


    @Override
    public ForumPostVO getPostById(Integer forumPostId) {
        Optional<ForumPostVO> postOptional = forumPostDao.findById(forumPostId);

        if (postOptional.isPresent()) {
            ForumPostVO post = postOptional.get();
            if (post.getForumPostStatus() == 0) {

                return post;
            }
        }
        return null;
    }


    @Override
    public List<ForumPostVO> findPostsByMemberId(Integer memberId) {

        return forumPostDao.findByMemberIdAndForumPostStatus(memberId, 0);
    }
    @Override
    public boolean togglePostStatus(Integer postId) {
        Optional<ForumPostVO> optionalPost = forumPostDao.findById(postId);

        if (optionalPost.isPresent()) {
            ForumPostVO post = optionalPost.get();
            post.setForumPostStatus(1);
            ForumPostVO updatedPost = forumPostDao.save(post);

            return updatedPost != null;
        }

        return false;
    }


    @Override
    @Transactional
    public boolean incrementClickCount(Integer postId) {
        Optional<ForumPostVO> optionalPost = forumPostDao.findById(postId);

        if (optionalPost.isPresent()) {
            ForumPostVO post = optionalPost.get();
            post.setForumPostClickCount(post.getForumPostClickCount() + 1);
            ForumPostVO updatedPost = forumPostDao.save(post);

            return updatedPost != null;
        }

        return false;
    }


    @Override
    public List<ForumPostVO> findPopularPosts() {

        return forumPostDao.findByForumPostStatusOrderByForumPostClickCountAsc(0);
    }

}
