package tw.com.cha102.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.product.model.entity.ProductVO;
import tw.com.cha102.product.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/backend")
public class BackendProduct {
    @Autowired
    ProductService service;

    //商品新增
    @PostMapping("/create")
    public ProductVO createProduct(@RequestBody ProductVO productVO){
        ProductVO vo=new ProductVO();
        if(service.addProduct(productVO) ==true){
            vo.setSuccessful(true);
            vo.setMessage("新增成功");
            return vo;
        }else{
            vo.setSuccessful(false);
            vo.setMessage("新增失敗 請重新輸入");
            return vo;
        }
    }
    //商品查詢狀態
    @GetMapping("/get/{productStatus}")
    public List<ProductVO> getProductByStatus(@PathVariable int productStatus){
        List<ProductVO> list = new ArrayList<ProductVO>();
        list=service.findProduct(productStatus);
            return list;
    }


}
