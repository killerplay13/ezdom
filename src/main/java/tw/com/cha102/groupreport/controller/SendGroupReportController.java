package tw.com.cha102.groupreport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.cha102.groupreport.model.GroupReportCreate;
import tw.com.cha102.groupreport.model.GroupReportVO;
import tw.com.cha102.groupreport.service.GroupReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/frontend/groupReport")
public class SendGroupReportController {
    @Autowired
    private GroupReportService groupReportService;


    @PostMapping("/createGroupReport")
    public ResponseEntity<String> createGroupReport(HttpServletRequest request,@RequestBody GroupReportCreate groupReportCreate){
        HttpSession session = request.getSession();
        Integer memberId = (Integer)session.getAttribute("memberId");
        GroupReportCreate result = groupReportService.addGroupReport(memberId,groupReportCreate);
        return ResponseEntity.ok("送出檢舉成功");
    }
}
