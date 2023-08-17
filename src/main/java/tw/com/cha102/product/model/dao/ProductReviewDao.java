package tw.com.cha102.product.model.dao;

import tw.com.cha102.product.model.entity.ProductReviewVO;


import java.util.List;

public interface ProductReviewDao {

    public int insert(ProductReviewVO productReviewVO);

    public int deleteById(Integer reviewId);

    public int update(ProductReviewVO productReviewVO);

    public ProductReviewVO selectById(Integer reviewId);

    public List<ProductReviewVO> selectAll();
}
