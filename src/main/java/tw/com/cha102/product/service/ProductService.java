package tw.com.cha102.product.service;

import tw.com.cha102.product.model.entity.ProductVO;

import java.util.List;

public interface ProductService {

    public boolean addProduct(ProductVO productVO);

    public List<ProductVO> findProductsByStatus(int productStatus);

    public ProductVO getById(Integer productId);

    public boolean editProduct(ProductVO productVO);

    public boolean upOrdownProduct(Integer productId);


}
