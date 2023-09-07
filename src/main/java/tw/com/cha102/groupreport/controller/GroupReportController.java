package tw.com.cha102.groupreport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.groupreport.model.GroupReportDAOImpl;
import tw.com.cha102.groupreport.model.GroupReportJoin;
import tw.com.cha102.groupreport.model.GroupReportVO;
import tw.com.cha102.groupreport.model.UpdateReportRequest;
import tw.com.cha102.groupreport.service.GroupReportService;

import java.util.List;

@RestController
@RequestMapping("/groupReport")
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
            @PathVariable Integer groupReportId,
            @RequestBody UpdateReportRequest request) {
        Integer groupReportStatus = request.getGroupReportStatus();
        String rejectReason = request.getRejectReason();
        Integer employeeId = request.getEmployeeId();

        GroupReportVO result = groupReportService.updateGroupReportStatus(
                groupReportId, groupReportStatus, rejectReason, employeeId);

        return result;
    }

}
