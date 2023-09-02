package tw.com.cha102.group.model;

import javax.persistence.*;

@Entity
@Table(name = "group_collect")
public class GroupCollect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "groupcollect_id")
    private Integer groupCollectId;

    @Column(name = "groupId")
    private Integer groupId;

    @Column(name = "memberId")
    private Integer memberId;

    public Integer getGroupCollectId() {
        return groupCollectId;
    }

    public void setGroupCollectId(Integer groupCollectId) {
        this.groupCollectId = groupCollectId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
}
