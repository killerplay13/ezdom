package tw.com.cha102.groupcreate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tw.com.cha102.member.model.entity.Member;


import javax.persistence.*;
import java.sql.Date;
import java.util.Base64;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "group_member", catalog = "cha102g4_test")
public class GroupVerifyVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GROUP_MEMBER_ID")
    private Integer groupMemberId;
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", referencedColumnName = "MEMBER_ID")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "GROUP_ID", referencedColumnName = "GROUP_ID")
    private GroupCreateVO groupId;
    @Column(name = "GROUP_APPLY_STATUS")
    private Integer groupApplyStatus;
    @Column(name = "GROUP_APPLY_DATE")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Date groupApplyDate;
    @Column(name = "GROUP_COMMENT")
    private String groupComment;
    @Column(name = "GROUP_SIGN_INTRO")
    private String groupSignIntro;

    public String getMemberName() {
        return member != null ? member.getMemberName() : null;
    }
    public String getMemberPhotoBase64() {
        if (member != null && member.getMemberPhoto() != null) {
            byte[] memberPhoto = member.getMemberPhoto();
            return Base64.getEncoder().encodeToString(memberPhoto);
        }
        return null;
    }
    public String getGroupName() {
        return groupId != null ? groupId.getGroupName() : null;
    }

}
