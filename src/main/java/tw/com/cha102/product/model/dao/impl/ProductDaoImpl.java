package tw.com.cha102.product.model.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import tw.com.cha102.order.model.entity.OrderVO;
import tw.com.cha102.product.model.dao.ProductDao;
import tw.com.cha102.product.model.entity.ProductVO;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.nio.channels.SeekableByteChannel;
import java.util.List;
@Repository
public class ProductDaoImpl implements ProductDao {

    @PersistenceContext
    private Session session;

    @Override
    public int insert(ProductVO productVO) {
        session.persist(productVO);
        return 1;
    }

    @Override
    public int deleteById(Integer productId) {
        ProductVO productVO = session.load(ProductVO.class, productId);
        session.remove(productVO);
        return 1;
    }

    @Override
    public int update(ProductVO productVO) {
        final StringBuilder hql = new StringBuilder()
                .append("UPDATE ProductVO SET ");
        hql.append("categoryId=:categoryId ")
                .append("productName=:productName ")
                .append("productDescription=:productDescription ")
                .append("productOriginPrice=:productOriginPrice ")
                .append("productDiscountPrice=:productDiscountPrice ")
                .append("productImage=:productImage ")
                .append("lastUpdatedDate=NOW() ")
                .append("WHERE productId=:productId ");
        Query query = session.createQuery(hql.toString());
        return  query.setParameter("categoryId",productVO.getCategoryId())
                    .setParameter("productName",productVO.getProductName())
                    .setParameter("productDescription",productVO.getProductDescription())
                    .setParameter("productOriginPrice",productVO.getProductOriginPrice())
                    .setParameter("productDiscountPrice",productVO.getProductDiscountPrice())
                    .setParameter("productImage",productVO.getProductImage())
                    .setParameter("productId",productVO.getProductId())
                    .executeUpdate();

    }

    @Override
    public ProductVO selectById(Integer productId) {
        return session.get(ProductVO.class,productId);
    }

    @Override
    public List<ProductVO> selectAll() {
        final String hql = "FROM ProductVO ORDER BY productId";
        return session
                .createQuery(hql, ProductVO.class)
                .getResultList();
    }

    @Override
    public List<ProductVO> selectByStatus(int productStatus) {
        final String hql = "FROM ProductVO WHERE productStatus = :productStatus ORDER BY productId";
        return session.createQuery(hql, ProductVO.class)
                .setParameter("productStatus", (byte) productStatus)
                .getResultList();
    }

}
