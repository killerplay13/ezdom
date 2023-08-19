package tw.com.cha102.product.model.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import tw.com.cha102.product.model.dao.ProductReviewDao;
import tw.com.cha102.product.model.entity.ProductReviewVO;
import tw.com.cha102.product.model.entity.ProductVO;

import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class ProductReviewDaoImpl implements ProductReviewDao {

    @PersistenceContext
    private Session session;

    @Override
    public int insert(ProductReviewVO productReviewVO) {
        session.persist(productReviewVO);
        return 1;
    }

    @Override
    public int deleteById(Integer reviewId) {
        session.load(ProductReviewVO.class,reviewId);
        return 1;
    }

    @Override
    public int update(ProductReviewVO productReviewVO) {
        return 0;
    }

    @Override
    public ProductReviewVO selectById(Integer reviewId) {
        return session.get(ProductReviewVO.class,reviewId);
    }

    @Override
    public List<ProductReviewVO> selectAll() {
        final String hql = "FROM ProductReviewVO ORDER BY reviewId";
        return session
                .createQuery(hql, ProductReviewVO.class)
                .getResultList();
    }
}
