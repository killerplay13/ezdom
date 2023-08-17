package tw.com.cha102.support.model.entity;

import java.io.Serializable;

public class SupportVO implements Serializable {
    private Integer faqId;
    private String faqName;
    private String faqAns;
    private String faqTag;

    public  SupportVO(){};

    public SupportVO(String faqName, String faqAns, String faqTag){
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
