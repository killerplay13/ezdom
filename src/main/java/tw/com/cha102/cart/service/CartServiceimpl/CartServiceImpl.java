package tw.com.cha102.cart.service.CartServiceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tw.com.cha102.cart.model.CartItem;
import tw.com.cha102.cart.service.CartService;
import tw.com.cha102.product.model.entity.ProductVO;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    private HashOperations<String, Integer, CartItem> hashOperations;
    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }
    @Override
    public void addToCart(Integer memberId, ProductVO product, Integer quantity) {
        String cartKey = getCartKey(memberId);
        CartItem cartItem = hashOperations.get(cartKey, product.getProductId());
        if (cartItem == null) {
            cartItem = new CartItem(product, quantity);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }
        hashOperations.put(cartKey, product.getProductId(), cartItem);
    }
    @Override
    public void removeFromCart(Integer memberId, Integer productId) {
        String cartKey = getCartKey(memberId);
        hashOperations.delete(cartKey, productId);
    }
    @Override
    public List<CartItem> getCartItems(Integer memberId) {
        String cartKey = getCartKey(memberId);
        Map<Integer, CartItem> cartMap = hashOperations.entries(cartKey);
        return new ArrayList<>(cartMap.values());
    }
    @Override
    public List<CartItem> viewCart(Integer memberId) {
        String cartKey = getCartKey(memberId);
        Map<Integer, CartItem> cartMap = hashOperations.entries(cartKey);
        ArrayList<CartItem> cartItems = new ArrayList(cartMap.values());
        return cartItems;
    }

    private String getCartKey(Integer memberId) {
        return "cart:" + memberId;
    }
}

