package tw.com.cha102.group.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "`group`")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "create_member_id", nullable = false)//不准為空值
    private Integer createMemberId;

    @Column(name = "group_date", nullable = false)
    private Date groupDate;

    @Column(name = "group_location")
    private String groupLocation;

    @Column(name = "group_create", nullable = false, insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date groupCreate;

    @Column(name = "group_title", nullable = false)
    private String groupTitle;

    @Column(name = "group_notice", nullable = false)
    private String groupNotice;

    @Column(name = "group_content", nullable = false)
    private String groupContent;

    @Column(name = "group_name", nullable = false)
    private String groupName;

    @Lob
    @Column(name = "group_photo")
    private byte[] groupPhoto;

    @Column(name = "group_status")
    private Integer groupStatus;

    @Column(name = "group_deposit", nullable = false)
    private Integer groupDeposit;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name = "limit_number", nullable = false)
    private Integer limitNumber;

    @Column(name = "registered_number", nullable = false)
    private Integer registeredNumber;

    @Column(name = "create_fail_reason")
    private String createFailReason;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getCreateMemberId() {
        return createMemberId;
    }

    public void setCreateMemberId(Integer createMemberId) {
        this.createMemberId = createMemberId;
    }

    public Date getGroupDate() {
        return groupDate;
    }

    public void setGroupDate(Date groupDate) {
        this.groupDate = groupDate;
    }

    public String getGroupLocation() {
        return groupLocation;
    }

    public void setGroupLocation(String groupLocation) {
        this.groupLocation = groupLocation;
    }

    public Date getGroupCreate() {
        return groupCreate;
    }

    public void setGroupCreate(Date groupCreate) {
        this.groupCreate = groupCreate;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    public String getGroupNotice() {
        return groupNotice;
    }

    public void setGroupNotice(String groupNotice) {
        this.groupNotice = groupNotice;
    }

    public String getGroupContent() {
        return groupContent;
    }

    public void setGroupContent(String groupContent) {
        this.groupContent = groupContent;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public byte[] getGroupPhoto() {
        return groupPhoto;
    }

    public void setGroupPhoto(byte[] groupPhoto) {
        this.groupPhoto = groupPhoto;
    }

    public Integer getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(Integer groupStatus) {
        this.groupStatus = groupStatus;
    }

    public Integer getGroupDeposit() {
        return groupDeposit;
    }

    public void setGroupDeposit(Integer groupDeposit) {
        this.groupDeposit = groupDeposit;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getLimitNumber() {
        return limitNumber;
    }

    public void setLimitNumber(Integer limitNumber) {
        this.limitNumber = limitNumber;
    }

    public Integer getRegisteredNumber() {
        return registeredNumber;
    }

    public void setRegisteredNumber(Integer registeredNumber) {
        this.registeredNumber = registeredNumber;
    }

    public String getCreateFailReason() {
        return createFailReason;
    }

    public void setCreateFailReason(String createFailReason) {
        this.createFailReason = createFailReason;
    }
}
