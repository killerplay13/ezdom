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

    private Integer productPrice;

    /**
     * 等於quantity數量 * productPrice單價
     */
    private Integer productTotalPrice;


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
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }


    public Integer getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(Integer productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }



    public CartProductVO(Integer productId, Integer quantity, String productName, byte[] productMainImage, Integer productPrice, Integer productTotalPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.productName = productName;
        this.productMainImage = productMainImage;
        this.productPrice = productPrice;
        this.productTotalPrice = productTotalPrice;
    }
}

