package tw.com.cha102.member.model.entity;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name = "member", catalog = "cha102g4_test")
public class Member {

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
    private java.util.Date memberBirthday;
    @Column(name = "MEMBER_PHOTO")
    private byte[] memberPhoto;
    @Column(columnDefinition = "INT NOT NULL DEFAULT 0")
    private Integer point;
    @Column(name = "checkIN",columnDefinition = "TINYINT NOT NULL DEFAULT 0")
    private Integer checkIn;
    @Column(name = "MEMBER_STATUS",columnDefinition = "TINYINT NOT NULL DEFAULT 0")
    private Byte memberStatus;

}
