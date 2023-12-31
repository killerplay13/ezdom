package tw.com.cha102.forumhistory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.forumhistory.model.entity.ForumHistoryVO;
import tw.com.cha102.forumhistory.service.ForumHistoryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/frontend/forum")
public class ForumHistoryController {
    @Autowired
    private ForumHistoryService forumHistoryService;


    //點擊文章時自動新增瀏覽紀錄
    @PostMapping("/history")
    public ForumHistoryVO collectPost(@RequestBody ForumHistoryVO forumHistoryVO, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer memberId = (Integer)session.getAttribute("memberId");
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


    //刪除瀏覽紀錄
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


    //列出我的文章瀏覽紀錄
    @GetMapping("/my-history")
    public List<ForumHistoryVO> listHistoryPostsByMember(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer memberId = (Integer)session.getAttribute("memberId");
        return forumHistoryService.findByMemberId(memberId);
    }
}
