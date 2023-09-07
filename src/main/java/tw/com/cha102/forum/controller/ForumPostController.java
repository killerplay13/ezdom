package tw.com.cha102.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.forum.model.entity.ForumPostVO;
import tw.com.cha102.forum.service.ForumPostService;


import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/frontend/forum")
public class ForumPostController {

    private ForumPostService forumPostService;

    @Autowired
    public ForumPostController(ForumPostService forumPostService) {
        this.forumPostService = forumPostService;

    }


    @PostMapping("/post")//發布新文章
    public ForumPostVO createPost(@RequestBody ForumPostVO forumPostVO, HttpSession session) {
        //Integer memberId = (Integer) session.getAttribute("memberId");//要注意型別
        Integer memberId = 3;
        forumPostVO.setMemberId(memberId);
        forumPostVO.setForumPostStatus(0);
        ForumPostVO vo = new ForumPostVO();
        if (forumPostService.post(forumPostVO) == true) {
            vo.setSuccessful(true);
            vo.setMessage("文章發布成功");
        } else {
            vo.setSuccessful(false);
            vo.setMessage("文章發布失敗");
        }
        return vo;
    }

    @PutMapping("/edit/{postId}")//編輯指定文章
    public ForumPostVO editPost(@PathVariable Integer postId, @RequestBody ForumPostVO forumPostVO,HttpSession session) {
        //Integer memberId = (Integer) session.getAttribute("memberId");
        Integer memberId = 3;
        forumPostVO.setMemberId(memberId);
        ForumPostVO vo = new ForumPostVO();
        if (forumPostService.edit(postId, forumPostVO) == true) {
            vo.setSuccessful(true);
            vo.setMessage("文章修改成功");
        } else {
            vo.setSuccessful(false);
            vo.setMessage("文章修改失敗");
        }

        return vo;
    }


    @GetMapping("/list")//列出所有文章
    public List<ForumPostVO> listPosts() {

        return forumPostService.findAll();
    }

    @DeleteMapping("/delete/post/{postId}")//刪除指定文章
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



    @GetMapping("/get/{postId}")//取得指定ID的文章
    public ForumPostVO getPostById(@PathVariable Integer postId) {
        return forumPostService.getPostById(postId);

    }

    @GetMapping("/my-posts") //列出指定會員ID的文章
    public List<ForumPostVO> listMyPosts(HttpSession session) {
//        Integer memberId = (Integer) session.getAttribute("memberId");
        Integer memberId=3;
        return forumPostService.findPostsByMemberId(memberId);
    }


    @PostMapping("/click/{postId}") // 增加點擊次數
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


    @GetMapping("/popular")// 查詢熱門文章
    public List<ForumPostVO> listPopularPosts() {
        return forumPostService.findPopularPosts();
    }
}

