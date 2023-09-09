package tw.com.cha102.member.model.entity;



import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import tw.com.cha102.core.vo.Core;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name = "member", catalog = "cha102g4_test")
public class Member extends Core {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Integer memberId;
    @Column(name = "MEMBER_ACCOUNT")
    private String memberAccount;
    @Column(name = "MEMBER_PASSWORD")
    private String memberPassword;
    @Column(name = "MEMBER_NAME")
    private String memberName;
    @Column(name = "MEMBER_ADDRESS")
    private String memberAddress;
    @Column(name = "MEMBER_PHONE")
    private String memberPhone;
    @Column(name = "MEMBER_UID")
    private String memberUid;
    @Column(name = "MEMBER_EMAIL")
    private String memberEmail;
    @Column(name = "MEMBER_BIRTHDAY")
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Timestamp memberBirthday;
    @Column(name = "MEMBER_PHOTO")
    private byte[] memberPhoto;
    @Column(columnDefinition = "INT NOT NULL DEFAULT 0")
    private Integer point;
    @Column(name = "MEMBER_GENDER")
    private Byte memberGender;
    @Column(name = "INTRODUCTION")
    private String introduction;
    @Column(name = "CHECKIN",columnDefinition = "TINYINT NOT NULL DEFAULT 0")
    private Byte checkIn;
    @Column(name = "MEMBER_STATUS",columnDefinition = "TINYINT NOT NULL DEFAULT 0")
    private Byte memberStatus;
}
