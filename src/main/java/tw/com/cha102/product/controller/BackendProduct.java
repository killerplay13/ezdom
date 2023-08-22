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
    private ProductService service;

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
    //商品依狀態刷新後台頁面
    @GetMapping("/get/{productStatus}")
    public List<ProductVO> getProductByStatus(@PathVariable int productStatus){
        List<ProductVO> list = new ArrayList<ProductVO>();
        list=service.findProductsByStatus(productStatus);
            return list;
    }

    //編輯商品抓起對應ID資料
    @GetMapping("/getById/{productId}")
    public  ProductVO getById(@PathVariable Integer productId){
        return service.getById(productId);
    }

    //後台修改商品資料
    @PutMapping("/edit/{productId}")
    public ProductVO editProduct(@PathVariable Integer productId,@RequestBody ProductVO productVO) {
        productVO.setProductId(productId);
        ProductVO vo = new ProductVO();
        if (service.editProduct(productVO) == true) {
            vo.setSuccessful(true);
            vo.setMessage("修改成功");
            return vo;
        } else {
            vo.setSuccessful(false);
            vo.setMessage("修改失敗 請稍後在試");
            return vo;
        }
    }
        //商品上下架
        @PutMapping("/updown/{productId}")
        public ProductVO productUpOrDwon(@PathVariable Integer productId){
            ProductVO productVO=new ProductVO();
            if(service.upOrdownProduct(productId)==true){
                productVO.setMessage("操作成功");
                productVO.setSuccessful(true);
            }else{
                productVO.setMessage("操作失敗");
                productVO.setSuccessful(false);
            }
            return productVO;
        }

}
