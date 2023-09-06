package tw.com.cha102.forumhistory.model.entity;

import lombok.Data;
import tw.com.cha102.core.vo.Core;
import tw.com.cha102.forum.model.entity.ForumPostVO;

import javax.persistence.*;

@Entity
@Data
@Table(name = "FORUMHISTORY",catalog ="cha102g4_test")
public class ForumHistoryVO extends Core {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FORUMHISTORY_ID")
    private Integer forumHistoryId;

    @Column(name = "MEMBER_ID")
    private Integer memberId;

    @Column(name = "FORUMPOST_ID")
    private Integer forumPostId;

    @ManyToOne
    @JoinColumn(name = "FORUMPOST_ID", insertable = false, updatable = false)
    private ForumPostVO forumPost;
}
