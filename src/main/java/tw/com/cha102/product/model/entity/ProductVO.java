package tw.com.cha102.product.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import tw.com.cha102.core.vo.Core;
import tw.com.cha102.order.model.entity.OrderDetailVO;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "product",catalog ="cha102g4_test")
public class ProductVO extends Core {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Integer productId;

    @Column(name = "CATEGORY_ID")
    private Integer categoryId;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "PRODUCT_DESCRIPTION")
    private String productDescription;

    @Column(name = "PRODUCT_ORIGIN_PRICE")
    private Integer productOriginPrice;

    @Column(name = "PRODUCT_DISCOUNT_PRICE")
    private Integer productDiscountPrice;

    @Lob
    @Column(name = "PRODUCT_IMAGE")
    private byte[] productImage;

    @Column(name = "PRODUCT_STATUS",insertable = false)
    private byte productStatus;

    @Column(name = "SCORE_COUNTER",insertable = false)
    private Integer scoreCounter;

    @Column(name = "COM_COUNTER",insertable = false)
    private Integer comCounter;

    @Column(name = "CREATION_DATE",insertable = false)
    private Timestamp creationDate;

    @Column(name = "LAST_UPDATED_DATE")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp lastUpdatedDate;

    @ManyToOne // 指定多对一关系
    @JoinColumn(name = "CATEGORY_ID",insertable=false ,updatable = false) // 指定外键列
    private CategoryVO category;

    @OneToMany(mappedBy = "productVO")
    private List<OrderDetailVO> orderDetailVOS;

    public ProductVO() {

    }

    public CategoryVO getCategory() {
        return category;
    }

    public void setCategory(CategoryVO category) {
        this.category = category;
    }

    public ProductVO(Integer categoryId, String productName, String productDescription, Integer productOriginPrice, Integer productDiscountPrice, byte[] productImage, Timestamp lastUpdatedDate,CategoryVO category) {
        this.categoryId = categoryId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productOriginPrice = productOriginPrice;
        this.productDiscountPrice = productDiscountPrice;
        this.productImage = productImage;
        this.lastUpdatedDate = lastUpdatedDate;
        this.category=category;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Integer getProductOriginPrice() {
        return productOriginPrice;
    }

    public void setProductOriginPrice(Integer productOriginPrice) {
        this.productOriginPrice = productOriginPrice;
    }

    public Integer getProductDiscountPrice() {
        return productDiscountPrice;
    }

    public void setProductDiscountPrice(Integer productDiscountPrice) {
        this.productDiscountPrice = productDiscountPrice;
    }

    public byte[] getProductImage() {
        return productImage;
    }

    public void setProductImage(byte[] productImage) {
        this.productImage = productImage;
    }

    public byte getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(byte productStatus) {
        this.productStatus = productStatus;
    }

    public Integer getScoreCounter() {
        return scoreCounter;
    }

    public void setScoreCounter(Integer scoreCounter) {
        this.scoreCounter = scoreCounter;
    }

    public Integer getComCounter() {
        return comCounter;
    }

    public void setComCounter(Integer comCounter) {
        this.comCounter = comCounter;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }
}
