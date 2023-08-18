package tw.com.cha102.friend.model.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Table(name="friend", catalog="cha102g4_test")
public class FriendVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FRIEND_ID")
    private Integer friendId;
    @Column(name = "MEMBER_ID_A")
    private Integer memberIdA;
    @Column(name = "MEMBER_ID_B")
    private Integer memberIdB;
    @Column(name = "FRIEND_STATUS", insertable = false)
    private Byte friendStatus;

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public Integer getMemberIdA() {
        return memberIdA;
    }

    public void setMemberIdA(Integer memberIdA) {
        this.memberIdA = memberIdA;
    }

    public Integer getMemberIdB() {
        return memberIdB;
    }

    public void setMemberIdB(Integer memberIdB) {
        this.memberIdB = memberIdB;
    }

    public Byte getFriendStatus() {
        return friendStatus;
    }

    public void setFriendStatus(Byte friendStatus) {
        this.friendStatus = friendStatus;
    }
}
