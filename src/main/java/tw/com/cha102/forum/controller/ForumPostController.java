package tw.com.cha102.forum.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.forum.model.entity.ForumPostVO;
import tw.com.cha102.forum.service.ForumPostService;

import java.util.List;

@RestController
@RequestMapping("/forum")
public class ForumPostController {

    @Autowired
    private ForumPostService forumPostService;

    @PostMapping("/post")
    public ResponseEntity<String> createPost(@RequestBody ForumPostVO forumPostVO) {
        ForumPostVO result = forumPostService.post(forumPostVO);
        if (result.isSuccessful()) {
            return ResponseEntity.ok(result.getMessage()); // 返回插入结果的消息
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 返回服务器错误状态码
        }
    }

    @PutMapping("/edit/{postId}")
    public ResponseEntity<String> editPost(@PathVariable Integer postId, @RequestBody ForumPostVO forumPostVO) {
        forumPostVO.setForumPostId(postId);
        ForumPostVO result = forumPostService.edit(forumPostVO);
        if (result != null) {
            return ResponseEntity.ok(result.getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<ForumPostVO>> listPosts() {
        List<ForumPostVO> posts = forumPostService.findAll();
        return ResponseEntity.ok(posts);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Integer postId) {
        boolean deleted = forumPostService.delete(postId);
        if (deleted) {
            return ResponseEntity.ok("刪除成功");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get/{postId}")
    public ResponseEntity<ForumPostVO> getPostById(@PathVariable Integer postId) {
        ForumPostVO result = forumPostService.getPostById(postId);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/my-posts/{memberId}")
    public ResponseEntity<List<ForumPostVO>> listMyPosts(@PathVariable Integer memberId) {
        List<ForumPostVO> myPosts = forumPostService.findPostsByMemberId(memberId);
        return ResponseEntity.ok(myPosts);
    }
}


