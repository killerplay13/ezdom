package tw.com.cha102.cart.model.entity;

public class Cart {

    private Integer quantity;

    private Integer productId;

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

    public Cart() {

    }

    public Cart(Integer productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;

    }
}
