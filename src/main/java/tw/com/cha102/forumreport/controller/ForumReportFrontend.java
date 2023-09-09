package tw.com.cha102.forumreport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.forumreport.model.entity.ForumReportVO;
import tw.com.cha102.forumreport.service.ForumReportService;

import javax.servlet.http.HttpSession;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Frontend/forum")
public class ForumReportFrontend {
    @Autowired
    private ForumReportService forumReportService;


    //進行文章檢舉
    @PostMapping("/report")
    public ForumReportVO createReport(@RequestBody ForumReportVO forumReportVO, HttpSession session) {
        // Integer memberId = (Integer) session.getAttribute("memberId");
        Integer memberId=3;
        forumReportVO.setMemberId(memberId);
        ForumReportVO vo = new ForumReportVO();
        if (forumReportService.createReport(forumReportVO) == true) {
            vo.setSuccessful(true);
            vo.setMessage("檢舉成功");
        } else {
            vo.setSuccessful(false);
            vo.setMessage("您已經檢舉過了");
        }
        return vo;
    }
}
