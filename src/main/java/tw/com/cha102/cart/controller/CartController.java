package tw.com.cha102.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.cart.model.entity.CartVO;
import tw.com.cha102.cart.service.CartService;


import javax.servlet.http.HttpSession;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/frontend/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    //購物車添加商品
    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestParam Integer memberId, @RequestParam Integer productId,@RequestParam Integer quantity) {
        cartService.addToCart(memberId, productId,quantity);
        return ResponseEntity.ok("新增成功");
    }

    @GetMapping("/list/{memberId}")
    public CartVO list(@PathVariable Integer memberId) {
        return cartService.list(memberId);
    }

    @DeleteMapping("/delete/{memberId}/{productId}")
    public CartVO delete(@PathVariable Integer memberId,@PathVariable Integer productId){
        return cartService.delete(memberId,productId);
    }

    @PostMapping("/reduce")
    public CartVO reduceToCart(@RequestParam Integer memberId, @RequestParam Integer productId){
        return cartService.reduce(memberId,productId);
    }

    @DeleteMapping("/deleteAll/{memberId}")
    public CartVO checkout (@PathVariable Integer memberId){
        return cartService.deleteAll(memberId);
    }


}

