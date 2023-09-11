package tw.com.cha102.groupreport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.groupreport.model.GroupReportDAOImpl;
import tw.com.cha102.groupreport.model.GroupReportJoin;
import tw.com.cha102.groupreport.model.GroupReportVO;
import tw.com.cha102.groupreport.model.UpdateReportRequest;
import tw.com.cha102.groupreport.service.GroupReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/backend/groupReport")
public class GroupReportController {
    @Autowired
    private GroupReportService groupReportService;


    @GetMapping("/listAllGroupReport")
    public List<GroupReportJoin> getAll(){
        List<GroupReportJoin> groupReportList = groupReportService.getAll();
        return groupReportList;
    }

    @PutMapping("/updateReportStatus/{groupReportId}")
    public GroupReportVO updateReportStatus(
            HttpServletRequest request,
            @PathVariable Integer groupReportId,
            @RequestBody UpdateReportRequest requestinfo) {
        Integer groupReportStatus = requestinfo.getGroupReportStatus();
        String rejectReason = requestinfo.getRejectReason();
        HttpSession session = request.getSession();
        String employeeId = (String) session.getAttribute("employeeId");
        GroupReportVO result = groupReportService.updateGroupReportStatus(
                groupReportId, groupReportStatus, rejectReason, employeeId);

        return result;
    }

}
