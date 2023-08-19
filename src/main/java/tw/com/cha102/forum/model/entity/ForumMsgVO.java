package tw.com.cha102.forum.model.entity;

import lombok.*;
import tw.com.cha102.core.vo.Core;
//import tw.com.cha102.member.model.MemberVO;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Data
@Table(name = "FORUMMSG",catalog ="cha102g4_test")
public class ForumMsgVO extends Core {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FORUMMSG_ID")
    private Integer forumMsgId;

    @Column(name = "FORUMPOST_ID")
    private Integer forumPostId;

    @Column(name = "MEMBER_ID")
    private Integer memberId;

    @Column(name = "FORUMMSG_CONTENT")
    private String forumMsgContent;

    @Column(name = "FORUMMSG_TIME",insertable = false)
    private Timestamp forumMsgTime;

//    @ManyToOne
//    @JoinColumn(name = "FORUMPOST_ID", insertable = false, updatable = false)
//    private ForumPostVO forumPost;
//
//    @ManyToOne
//    @JoinColumn(name = "MEMBER_ID", insertable = false, updatable = false)
//    private MemberVO member;
}

