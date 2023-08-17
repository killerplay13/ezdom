package tw.com.cha102.coachregister.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "COACH_MEMBER", catalog = "cha102g4_test")
public class CoachmemberVO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COACH_ID")
    private Integer coachId;
    @Column(name = "MEMBER_ID")
    private Integer memberId;
    private String introduction;
    private byte[] picture;
    @Column(name = "STATUS", insertable = false)
    private byte status;
    @Column(name = "CREATE_TIME", insertable = false)
    private Timestamp createTime;

}
