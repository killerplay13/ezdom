package tw.com.cha102.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.cart.model.entity.CartVO;
import tw.com.cha102.cart.service.CartService;


import javax.servlet.http.HttpServletRequest;
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
    public ResponseEntity<String> addToCart(HttpServletRequest request,@RequestParam Integer productId, @RequestParam Integer quantity) {
        HttpSession session = request.getSession();
        Integer memberId = (Integer)session.getAttribute("memberId");
        cartService.addToCart(memberId, productId,quantity);
        return ResponseEntity.ok("新增成功");
    }

    @GetMapping("/list")
    public CartVO list(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer memberId = (Integer)session.getAttribute("memberId");
        return cartService.list(memberId);
    }

    @DeleteMapping("/delete/{productId}")
    public CartVO delete(HttpServletRequest request,@PathVariable Integer productId){
        HttpSession session = request.getSession();
        Integer memberId = (Integer)session.getAttribute("memberId");
        return cartService.delete(memberId,productId);
    }

    @PostMapping("/reduce")
    public CartVO reduceToCart(HttpServletRequest request, @RequestParam Integer productId){
        HttpSession session = request.getSession();
        Integer memberId = (Integer)session.getAttribute("memberId");
        return cartService.reduce(memberId,productId);
    }

    @DeleteMapping("/deleteAll")
    public CartVO checkout (HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer memberId = (Integer)session.getAttribute("memberId");
        return cartService.deleteAll(memberId);
    }


}

