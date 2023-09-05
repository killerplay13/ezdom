package tw.com.cha102.forumreport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.forum.service.ForumPostService;
import tw.com.cha102.forumreport.model.entity.ForumReportVO;
import tw.com.cha102.forumreport.service.ForumReportService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/forum")
public class ForumReportController {

    private ForumReportService forumReportService;
    private ForumPostService forumPostService;

    @Autowired
    public ForumReportController(ForumReportService forumReportService, ForumPostService forumPostService) {
        this.forumReportService = forumReportService;
        this.forumPostService = forumPostService;

    }


    @PostMapping("/report")
    public ForumReportVO createReport(@RequestBody ForumReportVO forumReportVO) {
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

    @GetMapping("/report/list")
    public List<ForumReportVO> listAllReports() {
        return forumReportService.getAllReports();
    }

    @GetMapping("/report/{reportId}")
    public ForumReportVO getReportById(@PathVariable Integer reportId) {
        return forumReportService.getReportById(reportId);

    }


    @DeleteMapping("/report/delete/{reportId}")
    public ForumReportVO deleteReport(@PathVariable Integer reportId) {
        ForumReportVO vo = new ForumReportVO();
        if (forumReportService.deleteReport(reportId) == true) {
            vo.setSuccessful(true);
            vo.setMessage("刪除成功");
        } else {
            vo.setSuccessful(false);
            vo.setMessage("刪除失敗");
        }
        return vo;
    }




//    @GetMapping("/hasReported/{forumPostId}/{memberId}")
//    public Boolean hasReported(@PathVariable Integer forumPostId, @PathVariable Integer memberId) {
//        return  forumReportService.hasReportedSamePost(forumPostId, memberId);
//    }

    @PutMapping("/updateStatusAndToggle/{reportId}/{postId}")
    public ForumReportVO updateStatusAndToggle(@PathVariable Integer reportId, @PathVariable Integer postId) {
        ForumReportVO vo=new ForumReportVO();
        if(forumReportService.updateReportStatus(reportId)&&forumPostService.togglePostStatus(postId)==true){
            vo.setSuccessful(true);
            vo.setMessage("更換狀態成功");
        }else{
            vo.setSuccessful(false);
            vo.setMessage("您已更換過狀態了");
        }
        return vo;

    }
}

