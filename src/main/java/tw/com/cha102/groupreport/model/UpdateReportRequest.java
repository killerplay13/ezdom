package tw.com.cha102.groupreport.model;

public class UpdateReportRequest {
    private Integer groupReportStatus;
    private String rejectReason;
    private Integer employeeId;

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

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
}
