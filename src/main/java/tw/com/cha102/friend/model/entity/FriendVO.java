package tw.com.cha102.friend.model.entity;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
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
}
