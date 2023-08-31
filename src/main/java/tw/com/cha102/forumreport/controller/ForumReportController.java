package tw.com.cha102.forumreport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.forumreport.model.entity.ForumReportVO;
import tw.com.cha102.forumreport.service.ForumReportService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/forum")
public class ForumReportController {

    private  ForumReportService forumReportService;

    @Autowired
    public ForumReportController(ForumReportService forumReportService) {
        this.forumReportService = forumReportService;
    }

    @PostMapping("/report")
    public ResponseEntity<String> createReport(@RequestBody ForumReportVO forumReportVO) {
        ForumReportVO result = forumReportService.createReport(forumReportVO);
        if (result.isSuccessful()) {
            return ResponseEntity.ok(result.getMessage()); // 返回插入结果的消息
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 返回服务器错误状态码
        }
    }

    @GetMapping("/report/list")
    public ResponseEntity<List<ForumReportVO>> listAllReports() {
        List<ForumReportVO> allReports = forumReportService.getAllReports();
        return ResponseEntity.ok(allReports);
    }

    @GetMapping("/report/{reportId}")
    public ResponseEntity<ForumReportVO> getReportById(@PathVariable Integer reportId) {
        ForumReportVO result = forumReportService.getReportById(reportId);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @DeleteMapping("/delete/report/{reportId}")
    public ResponseEntity<String> deleteReport(@PathVariable Integer reportId) {
        boolean deleted = forumReportService.deleteReport(reportId);
        if (deleted) {
            return ResponseEntity.ok("刪除檢舉成功");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
