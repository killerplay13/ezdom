package tw.com.cha102.groupreport.service;


import tw.com.cha102.groupreport.model.GroupReportDAO;
import tw.com.cha102.groupreport.model.GroupReportDAOImpl;
import tw.com.cha102.groupreport.model.GroupReportVO;

import java.util.List;
import java.util.Map;

public class GroupReportService {
    private GroupReportDAO dao;

    public GroupReportService() {
        dao = new GroupReportDAOImpl() {
            public GroupReportVO addGroupReport(Integer groupReportId,
                                       Integer reportMemberId,
                                       Integer reportedMemberId,
                                       Integer groupId,
                                       String reportReason,
                                       Integer employeeId,
                                       Integer groupReportStatus,
                                       String rejectReason) {
                GroupReportVO groupReportVO = new GroupReportVO();
                groupReportVO.setGroupReportId(groupReportId);
                groupReportVO.setReportMemberId(reportMemberId);
                groupReportVO.setReportedMemberId(reportedMemberId);
                groupReportVO.setGroupId(groupId);
                groupReportVO.setReportReason(reportReason);
                groupReportVO.setEmployeeId(employeeId);
                groupReportVO.setGroupReportStatus(groupReportStatus);
                groupReportVO.setRejectReason(rejectReason);
                dao.insert(groupReportVO);
                return groupReportVO;
            }

            public GroupReportVO updateGroupReport(Integer groupReportId,
                                          Integer reportMemberId,
                                          Integer reportedMemberId,
                                          Integer groupId,
                                          String reportReason,
                                          Integer employeeId,
                                          Integer groupReportStatus,
                                          String rejectReason) {
                GroupReportVO groupReportVO = new GroupReportVO();
                groupReportVO.setGroupReportId(groupReportId);
                groupReportVO.setReportMemberId(reportMemberId);
                groupReportVO.setReportedMemberId(reportedMemberId);
                groupReportVO.setGroupId(groupId);
                groupReportVO.setReportReason(reportReason);
                groupReportVO.setEmployeeId(employeeId);
                groupReportVO.setGroupReportStatus(groupReportStatus);
                groupReportVO.setRejectReason(rejectReason);
                dao.update(groupReportVO);
                return groupReportVO;
            }


            public List<GroupReportVO> getAll() {
                return dao.getAll();
            }


            public List<GroupReportVO> getAll(Map<String, String[]> map) {
                return dao.getAll(map);
            }
        };
    }
}
