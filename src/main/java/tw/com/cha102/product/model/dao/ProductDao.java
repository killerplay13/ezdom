package tw.com.cha102.product.model.dao;


import tw.com.cha102.product.model.entity.ProductVO;

import java.util.List;

public interface ProductDao {

    public int insert(ProductVO productVO);

    public int deleteById(Integer productId);

    public int update(ProductVO productVO);

    public ProductVO selectById(Integer productId);

    public List<ProductVO> selectAll();

    public List<ProductVO> selectByStatus(int productStatus);

    public int updateToStatus(ProductVO productVO);
}
