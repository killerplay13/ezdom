package tw.com.cha102.cart.service.CartServiceimpl;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tw.com.cha102.cart.model.CartItem;
import tw.com.cha102.cart.model.entity.Cart;
import tw.com.cha102.cart.model.entity.CartProductVO;
import tw.com.cha102.cart.model.entity.CartVO;
import tw.com.cha102.cart.service.CartService;
import tw.com.cha102.core.vo.ResponseVO;
import tw.com.cha102.product.model.dao.ProductDao;
import tw.com.cha102.product.model.entity.ProductVO;

import java.math.BigDecimal;
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

    @Override
    public CartVO list(Integer memberId) {
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String cartKey = getCartKey(memberId);
//      這行程式碼使用 opsForHash.entries 從 Redis 中獲取指定鍵的所有數據。
        Map<String, String> entries = opsForHash.entries(cartKey);
        Integer cartTotalQuantity = 0;
        Integer cartTotalPrice = 0;
        CartVO cartVO = new CartVO();
        List<CartProductVO> cartProductVoList = new ArrayList<>();
        for (Map.Entry<String, String> entry : entries.entrySet()){
            Integer productId = Integer.valueOf(entry.getKey());
            Cart cart = gson.fromJson(entry.getValue(), Cart.class);

            ProductVO productVO = dao.selectById(productId);
            if(productVO != null){
                CartProductVO cartProductVo = new CartProductVO(productId,
                        cart.getQuantity(),
                        productVO.getProductName(),
                        productVO.getProductImage(),
                        productVO.getProductDiscountPrice(),
                        productVO.getProductStatus(),
                        productVO.getProductDiscountPrice()*(cart.getQuantity())
                        );
                cartProductVoList.add(cartProductVo);
                cartTotalPrice+=cartProductVo.getProductTotalPrice();
            }
            cartTotalQuantity += cart.getQuantity();
        }
        cartVO.setCartTotalQuantity(cartTotalQuantity);
        cartVO.setCartTotalPrice(cartTotalPrice);
        cartVO.setCartProductVoList(cartProductVoList);
        return cartVO;
    }

    @Override
    public CartVO delete(Integer memberId, Integer productId) {
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String cartKey = getCartKey(memberId);
        CartVO cartVO=new CartVO();
        String value = opsForHash.get(cartKey, String.valueOf(productId));
        if (value == null || value.trim().isEmpty()){
            cartVO.setMessage("購物車無此商品");
            cartVO.setSuccessful(false);
        }else {
            cartVO.setMessage("此商品已從購物車刪除");
            cartVO.setSuccessful(true);
        }
        opsForHash.delete(cartKey, String.valueOf(productId));
        return cartVO;
    }



    private String getCartKey(Integer memberId) {
        return "cart:" + memberId;
    }
}

