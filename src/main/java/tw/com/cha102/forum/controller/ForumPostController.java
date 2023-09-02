package tw.com.cha102.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.forum.dto.PostDTO;
import tw.com.cha102.forum.model.entity.ForumPostVO;
import tw.com.cha102.forum.service.ForumPostService;

import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/forum")
public class ForumPostController {

    private ForumPostService forumPostService;

    @Autowired
    public ForumPostController(ForumPostService forumPostService) {

        this.forumPostService = forumPostService;
    }

    //發布新文章
    @PostMapping("/post")
    public ResponseEntity<ForumPostVO> createPost(@RequestBody PostDTO postDTO, HttpSession session) {
        //Integer memberId = (Integer) session.getAttribute("memberId");
        Integer memberId=2;

        ForumPostVO result = forumPostService.post(postDTO, memberId); // 正確的參數順序
        result.setSuccessful(true);
        result.setMessage("文章發布成功");
        return ResponseEntity.ok(result);
    }


    //編輯指定文章
    @PutMapping("/edit/{postId}")
    public ResponseEntity<ForumPostVO> editPost(@PathVariable Integer postId, @RequestBody PostDTO postDTO) {
        // 實現 'editPost' 方法：編輯指定文章
        ForumPostVO result = forumPostService.edit(postId, postDTO);  // 使用修改後的方法
        result.setSuccessful(true);
        result.setMessage("文章修改成功");
        return ResponseEntity.ok(result);
    }

    // 其他方法...

    //列出所有文章
    @GetMapping("/list")
    public ResponseEntity<List<ForumPostVO>> listPosts() {
        List<ForumPostVO> posts = forumPostService.findAll();
        return ResponseEntity.ok(posts);
    }
    //刪除指定文章
    @DeleteMapping("/delete/post/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Integer postId) {
        boolean deleted = forumPostService.delete(postId);
        if (deleted) {
            return ResponseEntity.ok("刪除成功");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //取得指定ID的文章
    @GetMapping("/get/{postId}")
    public ResponseEntity<ForumPostVO> getPostById(@PathVariable Integer postId) {
        ForumPostVO result = forumPostService.getPostById(postId);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //列出指定會員ID的文章
    @GetMapping("/my-posts")
    public ResponseEntity<List<ForumPostVO>> listMyPosts(HttpSession session) {
        //Integer memberId = (Integer) session.getAttribute("memberId");
        Integer memberId=2;
        List<ForumPostVO> myPosts = forumPostService.findPostsByMemberId(memberId);
        return ResponseEntity.ok(myPosts);
    }

}

