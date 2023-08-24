package tw.com.cha102.support.model.entity;

import tw.com.cha102.core.vo.Core;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "faq")
public class FaqVO extends Core {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FAQ_ID")
    private Integer faqId;
    @Column(name = "FAQ_NAME")
    @NotNull
    private String faqName;
    @Column(name = "FAQ_ANS")
    @NotNull
    private String faqAns;
    @Column(name = "FAQ_TAG")
    private String faqTag;

    public FaqVO(){};

    public FaqVO(String faqName, String faqAns, String faqTag){
        this.faqName = faqName;
        this.faqAns = faqAns;
        this.faqTag = faqTag;
    }

    public Integer getFaqId() {
        return faqId;
    }

    public void setFaqId(Integer faqId) {
        this.faqId = faqId;
    }

    public String getFaqName() {
        return faqName;
    }

    public void setFaqName(String faqName) {
        this.faqName = faqName;
    }

    public String getFaqAns() {
        return faqAns;
    }

    public void setFaqAns(String faqAns) {
        this.faqAns = faqAns;
    }

    public String getFaqTag() {
        return faqTag;
    }

    public void setFaqTag(String faqTag) {
        this.faqTag = faqTag;
    }

    @Override
    public String toString() {
        return "SupportVO{" +
                "faqId=" + faqId +
                ", faqName='" + faqName + '\'' +
                ", faqAns='" + faqAns + '\'' +
                ", faqTag='" + faqTag + '\'' +
                '}';
    }
}
