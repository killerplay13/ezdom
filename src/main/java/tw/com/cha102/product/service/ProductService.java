package tw.com.cha102.product.service;

import tw.com.cha102.product.model.entity.ProductVO;

import java.util.List;

public interface ProductService {

    boolean addProduct(ProductVO productVO);

    List<ProductVO> findProductsByStatus(int productStatus);

    ProductVO getById(Integer productId);

    boolean editProduct(ProductVO productVO);

    boolean upOrdownProduct(Integer productId);

    List<ProductVO> showProducts(Integer value);

    int findProductCount();

    List<ProductVO> findProductByCategoryId(Integer value,Integer categoryId);

    int findProductCountByCategoryId(Integer categoryId);

    ProductVO findProductById (Integer productId);

    List<ProductVO> findProductByRand();
}
