package tw.com.cha102.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.cha102.forum.model.entity.ForumPostVO;
import tw.com.cha102.forum.model.dao.ForumPostDao;
import tw.com.cha102.forum.service.ForumPostService;

import java.util.List;

@Service
public class ForumPostServiceImpl implements ForumPostService {

    @Autowired
    private ForumPostDao forumPostDao;

    //發文方法
    @Override
    public ForumPostVO post(ForumPostVO forumPostVO) {
        if (forumPostVO.getForumPostTitle() == null || forumPostVO.getForumPostContent() == null) {//檢查標題和內容是否為空
            forumPostVO.setMessage("標題或內容未輸入");
            forumPostVO.setSuccessful(false);
            return forumPostVO;
        }


        ForumPostVO savedPost = forumPostDao.save(forumPostVO);//儲存文章並取得結果
        //在Spring Data JPA 中,save方法既可以用於新增資料記錄,也可以用於更新資料記錄,具體取決於傳遞給該方法的物件是否已經存在於資料庫中

        if (savedPost != null) {//根據儲存結果設定訊息
            savedPost.setMessage("發文成功");
            savedPost.setSuccessful(true);
            return savedPost;
        } else {
            forumPostVO.setMessage("發文錯誤");
            forumPostVO.setSuccessful(false);
            return forumPostVO;
        }
    }

    //編輯文章方法
    @Override
    public ForumPostVO edit(ForumPostVO forumPostVO) {
        ForumPostVO editPost = forumPostDao.findById(forumPostVO.getForumPostId()).orElse(null); //根據文章ID查詢現有文章

        if (editPost != null) {//如果存在現有文章,進行編輯
            editPost.setForumPostTitle(forumPostVO.getForumPostTitle());
            editPost.setForumPostContent(forumPostVO.getForumPostContent());

            ForumPostVO updatedPost = forumPostDao.save(editPost);//儲存編輯後的文章

            if (updatedPost != null) {//根據儲存結果設定訊息
                updatedPost.setMessage("修改文章成功");
                updatedPost.setSuccessful(true);
                return updatedPost;
            }
        }

        forumPostVO.setMessage("修改文章失敗");//編輯失敗或文章不存在,設定訊息並返回
        forumPostVO.setSuccessful(false);
        return forumPostVO;
    }

    //查詢所有文章
    @Override
    public List<ForumPostVO> findAll() {
        return forumPostDao.findAll();
    }

    @Override
    public boolean delete(Integer forumPostId) {//刪除文章
        ForumPostVO post = forumPostDao.findById(forumPostId).orElse(null);// 根據文章ID查詢現有文章
        if (post != null) {// 如果存在現有文章，進行刪除
            forumPostDao.delete(post);
            return true;
        }

        return false;
    }

    //儲存文章
    @Override
    public boolean save(ForumPostVO forumPostVO) {
        ForumPostVO savedPost = forumPostDao.save(forumPostVO);// 儲存文章並取得結果

        if (savedPost != null) { //根據儲存結果設定訊息
            savedPost.setMessage("儲存成功");
            savedPost.setSuccessful(true);
            return true;
        } else {
            return false;
        }
    }

    //根據文章ID查詢文章
    @Override
    public ForumPostVO getPostById(Integer forumPostId) {
        ForumPostVO post = forumPostDao.findById(forumPostId).orElse(null);
        return post;
    }

    //根據會員ID查詢文章列表
    @Override
    public List<ForumPostVO> findPostsByMemberId(Integer memberId) {

        return forumPostDao.findByMemberId(memberId);
    }
}
