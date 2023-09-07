package tw.com.cha102.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.product.model.entity.ProductVO;
import tw.com.cha102.product.service.ProductService;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/frontend/product")
public class FrontendProduct {
    @Autowired
    private ProductService service;

    @Autowired
    private ServletContext servletContext;

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

    //隨機抓取4筆商品 商品詳情頁
    @GetMapping("/rand")
    public  List<ProductVO> findProductByRand(){
        return service.findProductByRand();
    }

    //商品搜尋保存全部上架商品
    @GetMapping("/get")
    public void getAllProductByStatus(){
        servletContext.removeAttribute("AllProduct");
        int productStatus=1;
        List<ProductVO> list = new ArrayList<ProductVO>();
        list=service.findProductsByStatus(productStatus);
        servletContext.setAttribute("AllProduct",list);
    }

    @GetMapping("/find")
    public List<ProductVO> findProductByWord(@RequestParam String name){
        List<ProductVO> allProduct = (List<ProductVO>)(servletContext.getAttribute("AllProduct"));
        List<ProductVO> newList=allProduct.stream()
                .filter(e->e.getProductName().contains(name))
                .limit(5)
                .collect(Collectors.toList());
        return newList;
    }
}
