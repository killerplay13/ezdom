package tw.com.cha102.cart.service;

import tw.com.cha102.cart.model.CartItem;
import tw.com.cha102.product.model.entity.ProductVO;

import java.util.List;

public interface CartService {
    void addToCart(Integer memberId, ProductVO product, Integer quantity );

    void removeFromCart(Integer memberId, Integer productId);

    List<CartItem> getCartItems(Integer memberId);

    List<CartItem> viewCart(Integer memberId);
}
