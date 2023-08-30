package tw.com.cha102.product.model.dao;


import tw.com.cha102.product.model.entity.ProductVO;

import java.util.List;

public interface ProductDao {

    int insert(ProductVO productVO);

    int deleteById(Integer productId);

    int update(ProductVO productVO);

    ProductVO selectById(Integer productId);

    List<ProductVO> selectAll();

    List<ProductVO> selectByStatus(int productStatus);

    int updateToStatus(ProductVO productVO);

    List<ProductVO> selectBy12(Integer value);

    int selectProductCount();

    List<ProductVO> selectByCategoryId(Integer value,Integer categoryId);

    int selectProductCountByCategoryId(Integer categoryId);
}
