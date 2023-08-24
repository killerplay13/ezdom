package tw.com.cha102.groupcreate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tw.com.cha102.member.model.entity.Member;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupVerifyVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GROUP_MEMBER_ID")
    private Integer groupMemberId;
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", referencedColumnName = "MEMBER_ID")
    private Member member;
    @Column(name = "GROUP_ID")
    private Integer groupId;
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

}
