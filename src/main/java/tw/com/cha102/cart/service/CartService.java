package tw.com.cha102.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tw.com.cha102.cart.model.CartItem;
import tw.com.cha102.product.model.entity.ProductVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CartService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    private HashOperations<String, String, CartItem> hashOperations;

    public void addToCart(Integer memberId, ProductVO product, int quantity) {
        String cartKey = getCartKey(memberId);
        CartItem cartItem = hashOperations.get(cartKey, product.getProductId());
        if (cartItem == null) {
            cartItem = new CartItem(product, quantity);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }
        hashOperations.put(cartKey, String.valueOf(product.getProductId()), cartItem);
    }

    public void removeFromCart(Integer memberId, Integer productId) {
        String cartKey = getCartKey(memberId);
        hashOperations.delete(cartKey, productId);
    }

    public List<CartItem> getCartItems(Integer memberId) {
        String cartKey = getCartKey(memberId);
        Map<String, CartItem> cartMap = hashOperations.entries(cartKey);
        return new ArrayList<>(cartMap.values());
    }

    private String getCartKey(Integer memberId) {
        return "cart:" + memberId;
    }
}

