package tw.com.cha102.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.cart.model.CartItem;
import tw.com.cha102.cart.model.entity.CartVO;
import tw.com.cha102.cart.service.CartService;
import tw.com.cha102.core.vo.ResponseVO;
import tw.com.cha102.product.model.entity.ProductVO;

import javax.servlet.http.HttpSession;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    //購物車添加商品
    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestParam Integer memberId, @RequestParam Integer productId) {
        cartService.addToCart(memberId, productId);
        return ResponseEntity.ok("Item added to cart");
    }

    @GetMapping("/list/{memberId}")
    public CartVO list(@PathVariable Integer memberId) {
        return cartService.list(memberId);
    }

    @DeleteMapping("/delete/{memberId}/{productId}")
    public CartVO delete(@PathVariable Integer memberId,@PathVariable Integer productId){
        return cartService.delete(memberId,productId);
    }
}

