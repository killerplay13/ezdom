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
    //前台商品進入頁面刷新
    @GetMapping("/show/{value}")
    public List<ProductVO>showProduct(@PathVariable Integer value){
        return service.showProducts(value);
    }

    //前台全部商品分頁按鈕
    @GetMapping("/total")
    public int productTotalCount(){
        return service.findProductCount();
    }

    //前台商品分類 依照分類取12筆
    @GetMapping("/sort")
    public List<ProductVO> findProduct12ByCategoryId(@RequestParam Integer value,@RequestParam Integer categoryId){
        return service.findProductByCategoryId(value,categoryId);
    }

    //前台分類商品分頁按鈕
    @GetMapping("/sortPage")
    public int productTotalCountByCategoryId(@RequestParam Integer categoryId){
        return service.findProductCountByCategoryId(categoryId);
    }

    //商品詳情
    @GetMapping("/content/{productId}")
    public ProductVO findProductContnet(@PathVariable Integer productId){
       return service.findProductById(productId);
    }
}
