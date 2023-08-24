package tw.com.cha102.forum.model.entity;

import lombok.*;
import tw.com.cha102.core.vo.Core;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Data
@Table(name = "FORUMREPORT" ,catalog ="cha102g4_test")
public class ForumReportVO extends Core {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FORUMREPORT_ID")
    private Integer forumReportId;

    @Column(name = "FORUMPOST_ID")
    private Integer forumPostId;

    @Column(name = "MEMBER_ID")
    private Integer memberId;

    @Column(name = "FORUMREPORT_WHY")
    private String forumReportWhy;

    @Column(name = "FORUMREPORT_TIME",insertable = false)
    private Timestamp forumReportTime;

    @Column(name = "FORUMREPORT_STATUS",insertable = false)
    private Integer forumReportStatus;

    // @ManyToOne
    // @JoinColumn(name = "FORUMPOST_ID", insertable = false, updatable = false)
    // private ForumPostVO forumPost;


    // @ManyToOne
    // @JoinColumn(name = "MEMBER_ID", insertable = false, updatable = false)
    // private MemberVO member;

}

