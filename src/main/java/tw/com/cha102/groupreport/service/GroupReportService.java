package tw.com.cha102.groupreport.service;


import org.springframework.stereotype.Service;
import tw.com.cha102.core.service.MailService;
import tw.com.cha102.groupreport.model.*;

import java.util.List;

@Service
public class GroupReportService {
    private GroupReportDAO dao;

    public GroupReportService() {
        dao = new GroupReportDAOImpl() {
//            public GroupReportVO addGroupReport(Integer groupReportId,
//                                       Integer reportMemberId,
//                                       Integer reportedMemberId,
//                                       Integer groupId,
//                                       String reportReason,
//                                       Integer employeeId,
//                                       Integer groupReportStatus,
//                                       String rejectReason) {
//                GroupReportVO groupReportVO = new GroupReportVO();
//                groupReportVO.setGroupReportId(groupReportId);
//                groupReportVO.setReportMemberId(reportMemberId);
//                groupReportVO.setReportedMemberId(reportedMemberId);
//                groupReportVO.setGroupId(groupId);
//                groupReportVO.setReportReason(reportReason);
//                groupReportVO.setEmployeeId(employeeId);
//                groupReportVO.setGroupReportStatus(groupReportStatus);
//                groupReportVO.setRejectReason(rejectReason);
//                dao.insert(groupReportVO);
//                return groupReportVO;
//            }

            public GroupReportVO updateGroupReport(Integer groupReportId,
                                          Integer reportMemberId,
                                          Integer reportedMemberId,
                                          Integer groupId,
                                          String reportReason,
                                          String employeeId,
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


//            public List<GroupReportVO> getAll() {
//                return dao.getAll();
//            }


//            public List<GroupReportVO> getAll(Map<String, String[]> map) {
//                return dao.getAll(map);
//            }
        };
    }


    public List<GroupReportJoin> getAll() {
        return dao.getAll();
    }

    public GroupReportVO updateGroupReportStatus(Integer groupReportId, Integer groupReportStatus, String rejectReason, String employeeId) {
        GroupReportVO existingReport = dao.findById(groupReportId);
        GroupReportJoin infoForMail = dao.findMoreById(groupReportId);
        if (existingReport == null) {
            return null;
        }
        existingReport.setEmployeeId(employeeId);
        existingReport.setGroupReportStatus(groupReportStatus);
        existingReport.setRejectReason(rejectReason);
        dao.update(existingReport);
        if (groupReportStatus == 1){
            String to = infoForMail.getReportedEmail();
            String subject = "檢舉通知";
            String name = infoForMail.getReportedName();
            String groupName = infoForMail.getGroupName();
            String messageText = "Hello! " + name + "請注意!!!你已經被檢舉"+"\n"+
                    "你在 " + groupName + " 的行為已經引起他人不滿，下次請注意"+"\n"+
                    "若行為未改善，將影響您參加揪團的權益，謝謝!!";
            MailService mailService = new MailService();
            mailService.sendMail(to, subject, messageText);
        }
        if (groupReportStatus == 2){
            String to = infoForMail.getReportEmail();
            String subject = "檢舉結果通知";
            String name = infoForMail.getReportName();
            String groupName = infoForMail.getGroupName();
            String reportedName = infoForMail.getReportedName();
            String messageText = "Hello! " + name + "很遺憾地通知您:"+"\n"+
                    "您在 "+ groupName + "中對" + reportedName + "的檢舉未通過" +"\n"+
                    " 若下次遇到任何違反團規及善良風俗的團員，請依然不吝提出"+"\n"+
                    "EZDOM 團隊會盡最大的能力維護揪團環境，再次感謝您參加EZDOM的揪團，期待我們在EZDOM再相遇。";
            MailService mailService = new MailService();
            mailService.sendMail(to, subject, messageText);
        }
        return existingReport;
    }



    public GroupReportVO findById(Integer groupReportId) {
        return dao.findById(groupReportId);
    }

    public GroupReportCreate addGroupReport(
            Integer memberId,
            GroupReportCreate groupReportCreate
    ) {
        GroupReportCreate result = new GroupReportCreate();
        groupReportCreate.setReportMemberId(memberId);
        groupReportCreate.setReportedMemberId(groupReportCreate.getReportedMemberId());
        groupReportCreate.setGroupId(groupReportCreate.getGroupId());
        groupReportCreate.setReportReason(groupReportCreate.getReportReason());
        groupReportCreate.setGroupReportStatus(groupReportCreate.getGroupReportStatus());
        dao.insert(groupReportCreate);
        return result;
    }
}
