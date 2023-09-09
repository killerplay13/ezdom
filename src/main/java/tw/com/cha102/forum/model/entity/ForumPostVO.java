package tw.com.cha102.forum.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import tw.com.cha102.core.vo.Core;
import tw.com.cha102.forumcollect.model.entity.ForumCollectVO;
import tw.com.cha102.forumhistory.model.entity.ForumHistoryVO;
import tw.com.cha102.forummsg.model.entity.ForumMsgVO;
import tw.com.cha102.forumreport.model.entity.ForumReportVO;
import tw.com.cha102.member.model.entity.Member;

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

    @Column(name = "FORUMPOST_STATUS" )
    private  Integer forumPostStatus;

    @Column(name = "FORUMPOST_CLICK_COUNT")
    private Integer forumPostClickCount;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", insertable = false, updatable = false)
    private Member member;

    @OneToMany(mappedBy = "forumPost",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<ForumMsgVO> forumMsgs;

    @OneToMany(mappedBy = "forumPost", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<ForumCollectVO> forumCollects;

    @OneToMany(mappedBy = "forumPost", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<ForumReportVO> forumReports;

    @OneToMany(mappedBy = "forumPost", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<ForumHistoryVO> forumHistorys;

    //獲取memberName
    public String getMemberName() {
        return member != null ? member.getMemberName() : null;
    }

    //獲取memberPhoto
    public byte[] getMemberPhoto() {return member != null ? member.getMemberPhoto() : null;}
}


