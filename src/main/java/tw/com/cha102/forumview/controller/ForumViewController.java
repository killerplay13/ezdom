package tw.com.cha102.forumview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.forumview.model.entity.ForumViewVO;
import tw.com.cha102.forumview.service.ForumViewService;
import java.util.Set;

//@CrossOrigin(origins = "*")
//@RestController
//@RequestMapping("/forum")
public class ForumViewController {

//    private ForumViewService forumViewService;
//
//    @Autowired
//    public ForumViewController(ForumViewService forumViewService) {
//        this.forumViewService = forumViewService;
//    }
//
//    @PostMapping("/view")
//    public void addView(@RequestBody ForumViewVO view) {
//        forumViewService.addView(view);
//    }
//
//    @GetMapping("/view/recent/{memberId}")
//    public Set<ForumViewVO> getRecentViewedForumPosts(@PathVariable Integer memberId,
//                                                      @RequestParam long fromTimestamp,
//                                                      @RequestParam long toTimestamp) {
//        return forumViewService.getRecentViewedForumPosts(memberId, fromTimestamp, toTimestamp);
//    }
//
//    @DeleteMapping("/view/delete/{memberId}/{forumPostId}")
//    public void deleteView(@PathVariable Integer memberId, @PathVariable Integer forumPostId) {
//        forumViewService.deleteView(memberId, forumPostId);
//    }
}