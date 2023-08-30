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

    //發布新文章
    @Override
    public ForumPostVO post(ForumPostVO forumPostVO) {
        // 檢查文章標題和內容是否存在
        if (forumPostVO.getForumPostTitle() == null || forumPostVO.getForumPostContent() == null) {
            forumPostVO.setMessage("標題或內容未輸入");
            forumPostVO.setSuccessful(false);
        } else {
            //呼叫DAO進行文章儲存
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

    //編輯指定文章
    @Override
    public ForumPostVO edit(ForumPostVO forumPostVO) {
        Optional<ForumPostVO> existingPostOptional = forumPostDao.findById(forumPostVO.getForumPostId());

        if (existingPostOptional.isPresent()) {
            ForumPostVO existingPost = existingPostOptional.get();
            //更新文章標題、內容和類型
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
