package tw.com.cha102.product.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRODUCT_REVIEW")
public class ProductReviewVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REVIEW_ID")
    private Integer reviewId;

    @Column(name = "PRODUCT_ID")
    private Integer productId;

    @Column(name = "MEMBER_ID")
    private Integer memberId;

    private Integer score;

    @Column(name = "EVALUATE_DATE",insertable = false)
    private LocalDateTime evaluateDate;

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public LocalDateTime getEvaluateDate() {
        return evaluateDate;
    }

    public void setEvaluateDate(LocalDateTime evaluateDate) {
        this.evaluateDate = evaluateDate;
    }

    public ProductReviewVO(Integer productId, Integer memberId, Integer score) {
        this.productId = productId;
        this.memberId = memberId;
        this.score = score;
    }
}
