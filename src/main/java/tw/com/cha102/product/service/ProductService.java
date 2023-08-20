package tw.com.cha102.product.service;

import tw.com.cha102.product.model.entity.ProductVO;

import java.util.List;

public interface ProductService {

    public boolean addProduct(ProductVO productVO);

    public List<ProductVO> findProduct(int productStatus);
}
