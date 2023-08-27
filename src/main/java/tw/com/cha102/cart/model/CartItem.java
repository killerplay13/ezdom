package tw.com.cha102.cart.model;

import lombok.Data;
import tw.com.cha102.product.model.entity.ProductVO;

import java.io.Serializable;

@Data
public class CartItem implements Serializable {
    private ProductVO product;
    private Integer quantity;


    public CartItem(ProductVO product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;

    }
}
