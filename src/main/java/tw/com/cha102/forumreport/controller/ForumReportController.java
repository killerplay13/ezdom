package tw.com.cha102.forumreport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.forum.service.ForumPostService;
import tw.com.cha102.forumreport.model.entity.ForumReportVO;
import tw.com.cha102.forumreport.service.ForumReportService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/backend/forum")
public class ForumReportController {
    @Autowired
    private ForumReportService forumReportService;
    @Autowired
    private ForumPostService forumPostService;


    //查看所有檢舉
    @GetMapping("/report/list")
    public List<ForumReportVO> listAllReports() {

        return forumReportService.getAllReports();
    }


    //刪除檢舉
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


    //更新檢舉狀態
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

