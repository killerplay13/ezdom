package tw.com.cha102.product.model.dao;

import tw.com.cha102.product.model.entity.ProductReviewVO;


import java.util.List;

public interface ProductReviewDao {

    int insert(ProductReviewVO productReviewVO);

    int deleteById(Integer reviewId);

    int update(ProductReviewVO productReviewVO);

    ProductReviewVO selectById(Integer reviewId);

    List<ProductReviewVO> selectAll();
}
