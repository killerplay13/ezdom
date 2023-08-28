package tw.com.cha102.group.model;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
//修改揪團審核狀態存在的
public class AdminGroup {

    @NotNull
    @Column(name = "group_status")
    private Integer groupStatus;

    @Column(name = "create_fail_reason")
    private String createFailReason;

    public Integer getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(Integer groupStatus) {
        this.groupStatus = groupStatus;
    }

    public String getCreateFailReason() {
        return createFailReason;
    }

    public void setCreateFailReason(String createFailReason) {
        this.createFailReason = createFailReason;
    }
}
