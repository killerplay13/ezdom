package tw.com.cha102.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public ForumPostVO post(ForumPostVO forumPostVO) {
        // 檢查文章標題和內容是否存在
        if (forumPostVO.getForumPostTitle() == null || forumPostVO.getForumPostContent() == null) {
            forumPostVO.setMessage("標題或內容未輸入");
            forumPostVO.setSuccessful(false);
        } else {
            ForumPostVO doPost = forumPostDao.save(forumPostVO);
            if (doPost != null) {
                forumPostVO.setMessage("發文成功");
                forumPostVO.setSuccessful(true);
            } else {
                forumPostVO.setMessage("發文錯誤");
                forumPostVO.setSuccessful(false);
            }
        }

        return forumPostVO;
    }



    @Override
    public ForumPostVO edit(ForumPostVO forumPostVO) {
        Optional<ForumPostVO> existingPostOptional = forumPostDao.findById(forumPostVO.getForumPostId());

        if (existingPostOptional.isPresent()) {
            ForumPostVO existingPost = existingPostOptional.get();
            existingPost.setForumPostTitle(forumPostVO.getForumPostTitle());
            existingPost.setForumPostContent(forumPostVO.getForumPostContent());
            existingPost.setForumPostType(forumPostVO.getForumPostType());

            ForumPostVO updatedPost = forumPostDao.save(existingPost);

            if (updatedPost != null) {
                forumPostVO.setMessage("修改文章成功");
                forumPostVO.setSuccessful(true);
            } else {
                forumPostVO.setMessage("修改文章失敗");
                forumPostVO.setSuccessful(false);
            }
        } else {
            forumPostVO.setMessage("文章不存在");
            forumPostVO.setSuccessful(false);
        }

        return forumPostVO;
    }


    @Override
    public List<ForumPostVO> findAll() {
        return forumPostDao.findAll();
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

    @Override
    public ForumPostVO getPostById(Integer forumPostId) {
        Optional<ForumPostVO> postOptional = forumPostDao.findById(forumPostId);
        return postOptional.get();
    }

    @Override
    public List<ForumPostVO> findPostsByMemberId(Integer memberId) {
        return forumPostDao.findByMemberId(memberId);
    }
}
