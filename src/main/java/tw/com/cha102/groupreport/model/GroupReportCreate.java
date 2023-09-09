package tw.com.cha102.groupreport.model;

public class GroupReportCreate {
    private Integer reportMemberId;
    private Integer reportedMemberId;
    private Integer groupId;
    private String reportReason;
    private Integer groupReportStatus;

    public GroupReportCreate() {
    }

    public GroupReportCreate(Integer reportMemberId, Integer reportedMemberId, Integer groupId, String reportReason, Integer groupReportStatus) {
        this.reportMemberId = reportMemberId;
        this.reportedMemberId = reportedMemberId;
        this.groupId = groupId;
        this.reportReason = reportReason;
        this.groupReportStatus = groupReportStatus;
    }

    public Integer getGroupReportStatus() {
        return groupReportStatus;
    }

    public void setGroupReportStatus(Integer groupReportStatus) {
        this.groupReportStatus = groupReportStatus;
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

    public String getReportReason() {
        return reportReason;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }
}
