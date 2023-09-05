package tw.com.cha102.ad.model.entity;


import tw.com.cha102.product.model.entity.ProductVO;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "AD")
public class AdVO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AD_ID")
    private int adId;


    @Column(name = "PRODUCT_ID")
    private Integer productId;

    @Lob
    @Column(name = "PRODUCT_PIC")
    private byte[] productPic;

    @Column(name = "START_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Column(name = "END_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    public AdVO(int adId, Integer productId, byte[] productPic, Date startTime, Date endTime) {
        this.adId = adId;
        this.productId = productId;
        this.productPic = productPic;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public AdVO() {
    }

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }



    public byte[] getProductPic() {
        return productPic;
    }

    public void setProductPic(byte[] productPic) {
        this.productPic = productPic;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


    public Integer getProductId() {
        return productId;
    }
}

