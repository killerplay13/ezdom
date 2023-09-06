package tw.com.cha102.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.forum.model.entity.ForumPostVO;
import tw.com.cha102.forum.service.ForumPostService;


import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/forum")
public class ForumPostController {

    private final ForumPostService forumPostService;
//    private final ForumViewService forumViewService;

//    @Autowired
//    public ForumPostController(ForumPostService forumPostService, ForumViewService forumViewService) {
//        this.forumPostService = forumPostService;
//        this.forumViewService = forumViewService;
//    }
    @Autowired
    public ForumPostController(ForumPostService forumPostService) {
        this.forumPostService = forumPostService;

    }

    //發布新文章
    @PostMapping("/post")
    public ForumPostVO createPost(@RequestBody ForumPostVO forumPostVO, HttpSession session) {
        Integer memberId = 2; // 假设这里也使用固定的 memberId，你可以根据实际情况修改

//        // 在创建文章之前，获取 memberId 和 forumPostId
//        Integer forumPostId = forumPostVO.getForumPostId();
//
//        // 确保 memberId 和 forumPostId 的值在 Redis 和 MySQL 中一致
//        ForumViewVO view = new ForumViewVO();
//        view.setMemberId(memberId);
//        view.setForumPostId(forumPostId);
//        forumViewService.addView(view);

        ForumPostVO vo = new ForumPostVO();
        if (forumPostService.post(forumPostVO, memberId) == true) {
            vo.setSuccessful(true);
            vo.setMessage("文章發布成功");
        } else {
            vo.setSuccessful(false);
            vo.setMessage("文章發布失敗");
        }
        return vo;
    }

    @PutMapping("/edit/{postId}")
    public ForumPostVO editPost(@PathVariable Integer postId, @RequestBody ForumPostVO forumPostVO) {
        // 實現 'editPost' 方法：編輯指定文章
        Integer memberId = 2; // 假設這裡也使用固定的 memberId，你可以根據實際情況修改
        ForumPostVO vo = new ForumPostVO();
        if (forumPostService.edit(postId, forumPostVO) == true) { // 修改 'edit' 方法的參數和返回類型
            vo.setSuccessful(true);
            vo.setMessage("文章修改成功");
        } else {
            vo.setSuccessful(false);
            vo.setMessage("文章修改失敗");
        }

        // 在编辑文章之后，获取 forumPostId
//        Integer forumPostId = forumPostVO.getForumPostId();
//
//        // 确保 memberId 和 forumPostId 的值在 Redis 和 MySQL 中一致
//        ForumViewVO view = new ForumViewVO();
//        view.setMemberId(memberId);
//        view.setForumPostId(forumPostId);
//        forumViewService.addView(view);

        return vo;
    }


    // 其他方法...

    //列出所有文章
    @GetMapping("/list")
    public List<ForumPostVO> listPosts() {
        return  forumPostService.findAll();
    }
    //刪除指定文章
    @DeleteMapping("/delete/post/{postId}")
    public ForumPostVO deletePost(@PathVariable Integer postId) {
        ForumPostVO vo = new ForumPostVO();
        if (forumPostService.delete(postId)==true) {
            vo.setSuccessful(true);
            vo.setMessage("刪除成功");
        } else {
            vo.setSuccessful(false);
            vo.setMessage("刪除失敗");
        }
        return vo;
    }


    //取得指定ID的文章
    @GetMapping("/get/{postId}")
    public ForumPostVO getPostById(@PathVariable Integer postId) {
        return forumPostService.getPostById(postId);

    }
    //列出指定會員ID的文章
    @GetMapping("/my-posts")
    public List<ForumPostVO> listMyPosts(HttpSession session) {
        //Integer memberId = (Integer) session.getAttribute("memberId");
        Integer memberId=2;
        return forumPostService.findPostsByMemberId(memberId);
    }

    // 增加點擊次數
    @PostMapping("/click/{postId}")
    public ForumPostVO incrementClick(@PathVariable Integer postId) {
        ForumPostVO vo = new ForumPostVO();
        if (forumPostService.incrementClickCount(postId)) {
            vo.setSuccessful(true);
            vo.setMessage("文章點擊次數增加成功");
        } else {
            vo.setSuccessful(false);
            vo.setMessage("文章點擊次數增加失敗");
        }
        return vo;
    }

    // 查詢熱門文章
    @GetMapping("/popular")
    public List<ForumPostVO> listPopularPosts() {
        return forumPostService.findPopularPosts();
    }


}

