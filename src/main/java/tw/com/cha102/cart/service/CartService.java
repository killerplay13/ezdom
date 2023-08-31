package tw.com.cha102.cart.service;


import tw.com.cha102.cart.model.entity.CartVO;

import java.util.List;

public interface CartService {
    void addToCart(Integer memberId,Integer productId,Integer quantity);

    CartVO list(Integer memberId);

    CartVO delete(Integer memberId, Integer productId);

    CartVO reduce(Integer memberId,Integer productId);

    CartVO deleteAll(Integer memberId);
}
