package tw.com.cha102.cart.model;

import lombok.Data;
import tw.com.cha102.product.model.entity.ProductVO;
@Data
public class CartItem {
    private ProductVO product;
    private int quantity;

    public CartItem(ProductVO product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
