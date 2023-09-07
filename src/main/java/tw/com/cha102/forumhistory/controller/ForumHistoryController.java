package tw.com.cha102.forumhistory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.forumhistory.model.entity.ForumHistoryVO;
import tw.com.cha102.forumhistory.service.ForumHistoryService;

import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/frontend/forum")
public class ForumHistoryController {
    private ForumHistoryService forumHistoryService;

    @Autowired
    public ForumHistoryController(ForumHistoryService forumHistoryService) {
        this.forumHistoryService = forumHistoryService;
    }

    @PostMapping("/history")
    public ForumHistoryVO collectPost(@RequestBody ForumHistoryVO forumHistoryVO, HttpSession session) {
        //Integer memberId = (Integer) session.getAttribute("memberId");//要注意型別
        Integer memberId = 3;
        forumHistoryVO.setMemberId(memberId);
        ForumHistoryVO vo = new ForumHistoryVO();
        if (forumHistoryService.history(forumHistoryVO)==true) {
            vo.setSuccessful(true);
            vo.setMessage("新增紀錄成功");
        }else{
            vo.setSuccessful(false);
            vo.setMessage("新增紀錄失敗");
        }
        return vo;
    }

    @DeleteMapping("/delete/history/{historyId}")
    public ForumHistoryVO deleteHistoryPost(@PathVariable Integer historyId) {
        ForumHistoryVO vo=new ForumHistoryVO();
        if (forumHistoryService.delete(historyId)==true) {
            vo.setSuccessful(true);
            vo.setMessage("刪除成功");
        }else{
            vo.setSuccessful(false);
            vo.setMessage("刪除失敗");
        }
        return vo;
    }
    @GetMapping("/my-history")
    public List<ForumHistoryVO> listHistoryPostsByMember(HttpSession session) {
        //Integer memberId = (Integer) session.getAttribute("memberId");//要注意型別
        Integer memberId = 3;
        return forumHistoryService.findByMemberId(memberId);
    }
}
