package tw.com.cha102.forum.model.entity;

import lombok.*;
import tw.com.cha102.core.vo.Core;

import javax.persistence.*;


@Entity
@Data
@Table(name = "FORUMCOLLECT",catalog ="cha102g4_test")
public class ForumCollectVO extends Core {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FORUMCOLLECT_ID")
    private Integer forumCollectId;

    @Column(name = "FORUMPOST_ID")
    private Integer forumPostId;

    @Column(name = "MEMBER_ID")
    private Integer memberId;


//     @ManyToOne
//     @JoinColumn(name = "FORUMPOST_ID", insertable = false, updatable = false)
//     private ForumPostVO forumPost;


//     @ManyToOne
//     @JoinColumn(name = "MEMBER_ID", insertable = false, updatable = false)
//     private MemberVO member;
}

