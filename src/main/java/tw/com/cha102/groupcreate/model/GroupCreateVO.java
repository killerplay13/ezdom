package tw.com.cha102.groupcreate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tw.com.cha102.core.vo.Core;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GroupCreateVO extends Core {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GROUP_ID")
    private Integer groupId;
    @Column(name = "CREATE_MEMBER_ID")
    private Integer createMemberId;
    @Column(name = "GROUP_DATE")
    private Date groupDate;
    @Column(name = "GROUP_LOCATION")
    private String groupLocation;
    @Column(name = "GROUP_CREATE",insertable = false)
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp groupCreate;
    @Column(name = "GROUP_TITLE")
    private String groupTitle;
    @Column(name = "GROUP_NOTICE")
    private String groupNotice;
    @Column(name = "GROUP_CONTENT")
    private String groupContent;
    @Column(name = "GROUP_NAME")
    private String groupName;
    @Column(name = "GROUP_PHOTO")
    private byte[] groupPhoto;
    @Column(name = "GROUP_STATUS")
    private Integer groupStatus;
    @Column(name = "GROUP_DEPOSIT")
    private Integer groupDeposit;
    @Column(name = "START_DATE")
    private Date startDate;
    @Column(name = "END_DATE")
    private Date endDate;
    @Column(name = "LIMIT_NUMBER")
    private Integer limitNumber;
    @Column(name = "REGISTERED_NUMBER")
    private Integer registeredNumber;
    @Column(name = "CREATE_FAIL_REASON")
    private String createFailReason;

}
