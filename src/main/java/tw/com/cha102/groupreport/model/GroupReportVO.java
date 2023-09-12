package tw.com.cha102.groupreport.model;

import tw.com.cha102.groupcreate.model.GroupVerifyRepository;
import tw.com.cha102.groupcreate.model.GroupVerifyVO;
import tw.com.cha102.member.model.entity.Member;

import java.io.Serializable;
import java.util.List;

public class GroupReportVO implements Serializable {
    private Integer groupReportId;
    private Integer reportMemberId;
    private Integer reportedMemberId;
    private Integer groupId;
    private String reportReason;
    private String employeeId;
    private Integer groupReportStatus;
    private String rejectReason;

    public GroupReportVO(){}

    public GroupReportVO(Integer groupReportId, Integer reportMemberId, Integer reportedMemberId, Integer groupId, String reportReason, String employeeId, Integer groupReportStatus, String rejectReason) {
        this.groupReportId = groupReportId;
        this.reportMemberId = reportMemberId;
        this.reportedMemberId = reportedMemberId;
        this.groupId = groupId;
        this.reportReason = reportReason;
        this.employeeId = employeeId;
        this.groupReportStatus = groupReportStatus;
        this.rejectReason = rejectReason;
    }

    public Integer getGroupReportId() {
        return groupReportId;
    }

    public void setGroupReportId(Integer groupReportId) {
        this.groupReportId = groupReportId;
    }

    public Integer getReportMemberId() {
        return reportMemberId;
    }

    public void setReportMemberId(Integer reportMemberId) {
        this.reportMemberId = reportMemberId;
    }

    public Integer getReportedMemberId() {
        return reportedMemberId;
    }

    public void setReportedMemberId(Integer reportedMemberId) {
        this.reportedMemberId = reportedMemberId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getReportReason() {return reportReason;}

    public void setReportReason(String reportReason) {this.reportReason = reportReason;}

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getGroupReportStatus() {
        return groupReportStatus;
    }

    public void setGroupReportStatus(Integer groupReportStatus) {
        this.groupReportStatus = groupReportStatus;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

        //join member table 用
//    public tw.com.cha102.member.model.entity.Member getMember(){
//        tw.com.cha102.member.service.imp.MemberServiceImp MemSvc =new tw.com.cha102.member.service.imp.MemberServiceImp();
//        tw.com.cha102.member.model.entity.Member member = MemSvc.getMembers(memberId);
//        return Member;
//    }

        //join groupVerify table 用
//    public  tw.com.cha102.groupcreate.model.GroupVerifyVO getGroupVerifyVO(){
//        tw.com.cha102.groupcreate.service.GroupVerifyService groupVerifyService = new tw.com.cha102.groupcreate.service.GroupVerifyService() {
//            @Override
//            public List<GroupVerifyVO> findAllGroupVerifyByGroupId(List<String> groupIds) {
//                return null;
//            }
//        };
//        return null;
//    }
}
