package tw.com.cha102.cart.model.entity;

import java.math.BigDecimal;

public class CartProductVO {


    private Integer productId;

    /**
     * 購買的數量
     */
    private Integer quantity;

    private String productName;

    private String productMainImage;

    private Integer productDiscountPrice;

    private byte productStatus;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public String getProductMainImage() {
        return productMainImage;
    }

    public void setProductMainImage(String productMainImage) {
        this.productMainImage = productMainImage;
    }

    public Integer getProductPrice() {
        return productDiscountPrice;
    }

    public void setProductPrice(Integer productDiscountPrice) {
        this.productDiscountPrice = productDiscountPrice;
    }

    public byte getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(byte productStatus) {
        this.productStatus = productStatus;
    }

    public BigDecimal getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(BigDecimal productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public Boolean getProductSelected() {
        return productSelected;
    }

    public void setProductSelected(Boolean productSelected) {
        this.productSelected = productSelected;
    }

    /**
     * 等於quantity數量 * productPrice單價
     */
    private BigDecimal productTotalPrice;

    private Integer productStock;

    /**
     * 商品是否選中
     */
    private Boolean productSelected;

    public CartProductVO(Integer productId, Integer quantity, String productName, String productMainImage, Integer productDiscountPrice, byte productStatus, BigDecimal productTotalPrice, Integer productStock, Boolean productSelected) {
        this.productId = productId;
        this.quantity = quantity;
        this.productName = productName;
        this.productMainImage = productMainImage;
        this.productDiscountPrice = productDiscountPrice;
        this.productStatus = productStatus;
        this.productTotalPrice = productTotalPrice;
        this.productStock = productStock;
        this.productSelected = productSelected;
    }
}

