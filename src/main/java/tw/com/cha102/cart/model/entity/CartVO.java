package tw.com.cha102.cart.model.entity;

import lombok.Data;
import tw.com.cha102.core.vo.Core;


import java.util.List;

@Data
public class CartVO extends Core {

    private Integer cartTotalPrice;

    private Integer cartTotalQuantity;

    private List<CartProductVO> cartProductVoList;

    public List<CartProductVO> getCartProductVoList() {
        return cartProductVoList;
    }

    public void setCartProductVoList(List<CartProductVO> cartProductVoList) {
        this.cartProductVoList = cartProductVoList;
    }

    public Integer getCartTotalPrice() {
        return cartTotalPrice;
    }

    public void setCartTotalPrice(Integer cartTotalPrice) {
        this.cartTotalPrice = cartTotalPrice;
    }

    public Integer getCartTotalQuantity() {
        return cartTotalQuantity;
    }

    public void setCartTotalQuantity(Integer cartTotalQuantity) {
        this.cartTotalQuantity = cartTotalQuantity;
    }


}
