package tw.com.cha102.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.cha102.forum.model.entity.ForumPostVO;
import tw.com.cha102.forum.model.dao.ForumPostDao;
import tw.com.cha102.forum.service.ForumPostService;
import tw.com.cha102.forummsg.model.entity.ForumMsgVO;

import java.util.List;
import java.util.Optional;

@Service
public class ForumPostServiceImpl implements ForumPostService {

    @Autowired
    private ForumPostDao forumPostDao;

    //發布新文章
    @Override
    public boolean post(ForumPostVO forumPostVO) {
//        forumPostVO.setMemberId(memberId);
//        forumPostVO.setForumPostStatus(0);
        ForumPostVO savedPost = forumPostDao.save(forumPostVO);
        // 檢查savedPost是否為null來判斷保存是否成功
        return savedPost != null;
    }

    // 編輯指定文章
    @Override
    public boolean edit(Integer postId, ForumPostVO forumPostVO) {
        Optional<ForumPostVO> existingPostOptional = forumPostDao.findById(postId);

        if (existingPostOptional.isPresent()) {
            ForumPostVO existingPost = existingPostOptional.get();
            existingPost.setForumPostTitle(forumPostVO.getForumPostTitle());
            existingPost.setForumPostContent(forumPostVO.getForumPostContent());
            existingPost.setForumPostType(forumPostVO.getForumPostType());

            ForumPostVO updatedPost = forumPostDao.save(existingPost);

            // 檢查updatedPost是否為null來判斷編輯是否成功
            return updatedPost != null;
        }

        return false; // 如果文章不存在，則返回false
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

    //列出所有文章
//    @Override
//    public List<ForumPostVO> findAll() {
//        return forumPostDao.findAll();
//    }

    //取得指定ID的文章
//    @Override
//    public ForumPostVO getPostById(Integer forumPostId) {
//        Optional<ForumPostVO> postOptional = forumPostDao.findById(forumPostId);
//        return postOptional.get();
//    }

    //列出指定會員ID的文章
//    @Override
//    public List<ForumPostVO> findPostsByMemberId(Integer memberId) {
//
//        return forumPostDao.findByMemberId(memberId);
//
//    }
    @Override
    public List<ForumPostVO> findAll() {//列出所有文章

        return forumPostDao.findAllByForumPostStatus(0);
    }

    @Override
    public ForumPostVO getPostById(Integer forumPostId) {//取得指定ID的文章
        Optional<ForumPostVO> postOptional = forumPostDao.findById(forumPostId);

        if (postOptional.isPresent()) {
            ForumPostVO post = postOptional.get();
            if (post.getForumPostStatus() == 0) {
                // 設置相關的成員名稱
                post.setMemberName(post.getMember().getMemberName());

                return post;
            }
        }
        return null;
    }


    @Override
    public List<ForumPostVO> findPostsByMemberId(Integer memberId) {//列出指定會員ID的文章
        return forumPostDao.findByMemberIdAndForumPostStatus(memberId, 0);
    }
    @Override
    public boolean togglePostStatus(Integer postId) {
        ForumPostVO post = forumPostDao.findById(postId).orElse(null);

        if (post != null) {
            post.setForumPostStatus(1); //直接將文章狀態設置為 1
            ForumPostVO updatedPost = forumPostDao.save(post);

            //檢查操作是否成功
            return updatedPost != null;
        }

        //如果文章不存在或者其他原因導致操作無法執行，返回 false
        return false;
    }


//    @Override
//    public void togglePostStatus(Integer postId) {
//        ForumPostVO post = forumPostDao.findById(postId).orElse(null);
//
//        if (post != null) {
//            // 切換文章狀態
//            if (post.getForumPostStatus() == 0) {
//                post.setForumPostStatus(1);
//            } else {
//                post.setForumPostStatus(0);
//            }
//
//            forumPostDao.save(post);
//        }
//    }

    @Override
    @Transactional
    public boolean incrementClickCount(Integer postId) {
        ForumPostVO post = forumPostDao.findById(postId).orElse(null);

        if (post != null) {
            post.setForumPostClickCount(post.getForumPostClickCount() + 1);
            ForumPostVO updatedPost = forumPostDao.save(post);

            // 檢查操作是否成功
            return updatedPost != null;
        }

        return false;
    }

    @Override
    public List<ForumPostVO> findPopularPosts() {
        return forumPostDao.findByOrderByForumPostClickCountAsc();
    }

}
