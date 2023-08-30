package tw.com.cha102.group.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "group_member")
@Data
public class GroupMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_member_id")
    private Integer groupMemberId;

    @Column(name = "member_id",nullable = false)
    private Integer memberId;

    @Column(name = "group_id",nullable = false)
    private Integer groupId;

    @Column(name = "group_apply_status")
    private byte groupApplyStatus;

    @Column(name = "group_apply_date")
    private LocalDateTime groupApplyDate;

    @Column(name = "group_sign_intro")
    private String groupSignIntro;

    @PrePersist
    protected void onCreate() {
        groupApplyDate = LocalDateTime.now();
    }


}
