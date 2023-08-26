package tw.com.cha102.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.cart.model.CartItem;
import tw.com.cha102.cart.service.CartService;
import tw.com.cha102.product.model.entity.ProductVO;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;


    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestParam Integer memberId, @RequestBody ProductVO product, @RequestParam Integer quantity) {
        cartService.addToCart(memberId, product, quantity);
        return ResponseEntity.ok("Item added to cart");
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<List<CartItem>> getCart(@PathVariable Integer memberId) {
        List<CartItem> cartItems = cartService.getCartItems(memberId);
        return ResponseEntity.ok(cartItems);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFromCart(@RequestParam Integer memberId, @RequestParam Integer productId) {
        cartService.removeFromCart(memberId, productId);
        return ResponseEntity.ok("Item removed from cart");
    }

    @GetMapping("/view/{memberId}")
    public List<CartItem> viewCart(@PathVariable Integer memberId) {
        return cartService.viewCart(memberId);
    }
}

