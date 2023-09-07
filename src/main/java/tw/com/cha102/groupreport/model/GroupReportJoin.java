package tw.com.cha102.groupreport.model;

import java.io.Serializable;

public class GroupReportJoin implements Serializable {
    private Integer groupReportId;
    private Integer reportMemberId;
    private String reportName;
    private String reportEmail;
    private Integer reportedMemberId;
    private String reportedName;
    private String reportedEmail;
    private Integer groupId;
    private String groupName;
    private String reportReason;
    private Integer employeeId;
    private Integer groupReportStatus;
    private String rejectReason;

    public GroupReportJoin() {
    }

    public GroupReportJoin(Integer groupReportId, Integer reportMemberId, String reportName, String reportEmail, Integer reportedMemberId, String reportedName, String reportedEmail, Integer groupId, String groupName, String reportReason, Integer employeeId, Integer groupReportStatus, String rejectReason) {
        this.groupReportId = groupReportId;
        this.reportMemberId = reportMemberId;
        this.reportName = reportName;
        this.reportEmail = reportEmail;
        this.reportedMemberId = reportedMemberId;
        this.reportedName = reportedName;
        this.reportedEmail = reportedEmail;
        this.groupId = groupId;
        this.groupName = groupName;
        this.reportReason = reportReason;
        this.employeeId = employeeId;
        this.groupReportStatus = groupReportStatus;
        this.rejectReason = rejectReason;
    }

    public String getReportEmail() {
        return reportEmail;
    }

    public void setReportEmail(String reportEmail) {
        this.reportEmail = reportEmail;
    }

    public String getReportedEmail() {
        return reportedEmail;
    }

    public void setReportedEmail(String reportedEmail) {
        this.reportedEmail = reportedEmail;
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

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Integer getReportedMemberId() {
        return reportedMemberId;
    }

    public void setReportedMemberId(Integer reportedMemberId) {
        this.reportedMemberId = reportedMemberId;
    }

    public String getReportedName() {
        return reportedName;
    }

    public void setReportedName(String reportedName) {
        this.reportedName = reportedName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getReportReason() {
        return reportReason;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
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
