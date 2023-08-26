package tw.com.cha102.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.product.model.entity.ProductVO;
import tw.com.cha102.product.service.ProductService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")
public class FrontendProduct {
    @Autowired
    private ProductService service;
    //前台商品分頁
    @GetMapping("/show/{value}")
    public List<ProductVO>showProduct(@PathVariable Integer value){
        return service.showProducts(value);
    }

    //前台商品分頁按鈕
    @GetMapping("/total")
    public int productTotalCount(){
        return service.findProductCount();
    }
}
