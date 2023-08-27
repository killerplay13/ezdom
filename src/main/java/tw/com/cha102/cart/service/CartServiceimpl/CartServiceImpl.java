package tw.com.cha102.cart.service.CartServiceimpl;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tw.com.cha102.cart.model.entity.Cart;
import tw.com.cha102.cart.service.CartService;
import tw.com.cha102.product.model.dao.ProductDao;
import tw.com.cha102.product.model.entity.ProductVO;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ProductDao dao;
    private Gson gson = new Gson();

    @Override
    public void addToCart(Integer memberId, Integer productId) {
        Integer quantity = 1;
        ProductVO product = dao.selectById(productId);
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String cartKey = getCartKey(memberId);
        String productIdstr = String.valueOf(product.getProductId());

        Cart cart;
        String value = opsForHash.get(cartKey, productIdstr);
        if (value == null || value.trim().isEmpty()) {
            // 沒有該商品，新增一個商品
            cart = new Cart(product.getProductId(), quantity);
        } else {
            // 已經有商品，商品數量+1
            cart = gson.fromJson(value, Cart.class);
            cart.setQuantity(cart.getQuantity() + quantity);
        }
        opsForHash.put(cartKey,
                productIdstr,
                gson.toJson(cart));

    }
//    @Override
//    public void removeFromCart(Integer memberId, Integer productId) {
//        String cartKey = getCartKey(memberId);
//        opsForHash.delete(cartKey, productId);
//    }
//    @Override
//    public List<CartItem> getCartItems(Integer memberId) {
//        String cartKey = getCartKey(memberId);
//        Map<String, CartItem> cartMap = opsForHash.entries(cartKey);
//        return new ArrayList<>(cartMap.values());
//    }
//    @Override
//    public List<CartItem> viewCart(Integer memberId) {
//        String cartKey = getCartKey(memberId);
//        Map<String, CartItem> cartMap = opsForHash.entries(cartKey);
//        ArrayList<CartItem> cartItems = new ArrayList(cartMap.values());
//        return cartItems;
//    }

    private String getCartKey(Integer memberId) {
        return "cart:" + memberId;
    }
}

