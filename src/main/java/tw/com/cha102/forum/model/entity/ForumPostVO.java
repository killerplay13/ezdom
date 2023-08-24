package tw.com.cha102.forum.model.entity;

import lombok.*;
import tw.com.cha102.core.vo.Core;
import tw.com.cha102.member.model.entity.MemberVO;
//import tw.com.cha102.member.model.MemberVO;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Data
@Table(name = "FORUMPOST",catalog ="cha102g4_test")
public class ForumPostVO extends Core {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FORUMPOST_ID")
    private Integer forumPostId;

    @Column(name = "MEMBER_ID")
    private Integer memberId;


    @Column(name = "FORUMPOST_CONTENT")
    private String forumPostContent;

    @Column(name = "FORUMPOST_TITLE")
    private String forumPostTitle;

    @Column(name = "FORUMPOST_TIME" ,insertable = false)
    private Timestamp forumPostTime;

    @Column(name = "FORUMPOST_TYPE" )
    private Integer forumPostType;



//    @ManyToOne
//    @JoinColumn(name = "MEMBER_ID", insertable = false, updatable = false)
//    private MemberVO member;
//
//    @OneToMany(mappedBy = "forumPost")
//    private List<ForumMsgVO> forumMsgs;
//
//    @OneToMany(mappedBy = "forumPost")
//    private List<ForumCollectVO> forumCollects;
//
//    @OneToMany(mappedBy = "forumPost")
//    private List<ForumReportVO> forumReports;
}


