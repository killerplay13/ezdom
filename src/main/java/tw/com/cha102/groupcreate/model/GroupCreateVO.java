package tw.com.cha102.groupcreate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import tw.com.cha102.core.vo.Core;
import tw.com.cha102.member.model.entity.Member;

import javax.persistence.*;


import java.sql.Timestamp;
import java.util.Date;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Table(name = "GROUP", catalog = "cha102g4_test")
public class GroupCreateVO extends Core {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GROUP_ID")
    private Integer groupId;
//    @ManyToOne
//    @JoinColumn(name = "CREATE_MEMBER_ID")
@Column(name = "CREATE_MEMBER_ID")
    private Integer createMemberId;
    @Column(name = "GROUP_DATE")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "GMT+8")
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
    @Lob
    @Column(name = "GROUP_PHOTO")
    private byte[] groupPhoto;
    @Column(name = "GROUP_STATUS",insertable = false,columnDefinition = "TINYINT NOT NULL DEFAULT 0")
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
