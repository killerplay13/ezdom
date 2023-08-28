package tw.com.cha102.cart.model.entity;

import java.math.BigDecimal;

public class CartProductVO {


    private Integer productId;

    /**
     * 購買的數量
     */
    private Integer quantity;

    private String productName;

    private byte[] productMainImage;

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


    public byte[] getProductMainImage() {
        return productMainImage;
    }

    public void setProductMainImage(byte[] productMainImage) {
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

    public Integer getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(Integer productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }


    /**
     * 等於quantity數量 * productPrice單價
     */
    private Integer productTotalPrice;





    public CartProductVO(Integer productId, Integer quantity, String productName, byte[] productMainImage, Integer productDiscountPrice, byte productStatus, Integer productTotalPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.productName = productName;
        this.productMainImage = productMainImage;
        this.productDiscountPrice = productDiscountPrice;
        this.productStatus = productStatus;
        this.productTotalPrice = productTotalPrice;
    }
}

