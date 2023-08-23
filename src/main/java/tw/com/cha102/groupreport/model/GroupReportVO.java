package tw.com.cha102.groupreport.model;

import java.io.Serializable;

public class GroupReportVO implements Serializable {
    private Integer groupReportId;
    private Integer reportMemberId;
    private Integer reportedMemberId;
    private Integer groupId;
    private Integer employeeId;
    private Integer groupReportStatus;
    private String rejectReason;
    public GroupReportVO(){

    }

    public GroupReportVO(Integer groupReportId, Integer reportMemberId, Integer reportedMemberId, Integer groupId, Integer employeeId, Integer groupReportStatus, String rejectReason) {
        this.groupReportId = groupReportId;
        this.reportMemberId = reportMemberId;
        this.reportedMemberId = reportedMemberId;
        this.groupId = groupId;
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

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
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
}
