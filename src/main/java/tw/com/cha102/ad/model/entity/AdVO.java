package tw.com.cha102.ad.model.entity;


import tw.com.cha102.product.model.entity.ProductVO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "AD")
public class AdVO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AD_ID")
    private int adId;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private ProductVO productid;

    @Lob
    @Column(name = "PRODUCT_PIC")
    private byte[] productPic;

    @Column(name = "START_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Column(name = "END_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    public AdVO(int adId, ProductVO productid, byte[] productPic, Date startTime, Date endTime) {
        this.adId = adId;
        this.productid = productid;
        this.productPic = productPic;
        this.startTime = startTime;
        this.endTime = endTime;
    }

// Constructors, getters and setters

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
}

