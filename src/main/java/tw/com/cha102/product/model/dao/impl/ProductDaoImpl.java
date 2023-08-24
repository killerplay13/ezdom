package tw.com.cha102.product.model.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import tw.com.cha102.order.model.entity.OrderVO;
import tw.com.cha102.product.model.dao.ProductDao;
import tw.com.cha102.product.model.entity.ProductVO;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
        byte[] productImage = productVO.getProductImage();
        if (productImage != null) {
            hql.append("productImage=:productImage, ");
        }
        hql.append("categoryId=:categoryId, ")
                .append("productName=:productName, ")
                .append("productDescription=:productDescription, ")
                .append("productOriginPrice=:productOriginPrice, ")
                .append("productDiscountPrice=:productDiscountPrice, ")
                .append("lastUpdatedDate=NOW() ")
                .append("WHERE productId=:productId ");
        Query query = session.createQuery(hql.toString());
        if (productImage != null) {
            query.setParameter("productImage",productVO.getProductImage());
        }
        return  query.setParameter("categoryId",productVO.getCategoryId())
                    .setParameter("productName",productVO.getProductName())
                    .setParameter("productDescription",productVO.getProductDescription())
                    .setParameter("productOriginPrice",productVO.getProductOriginPrice())
                    .setParameter("productDiscountPrice",productVO.getProductDiscountPrice())
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

    @Override
    public int updateToStatus(ProductVO productVO) {
        Query query = session.createQuery("UPDATE ProductVO SET productStatus=:productStatus where productId=:productId")
                .setParameter("productStatus", productVO.getProductStatus()).setParameter("productId", productVO.getProductId());
        int i = query.executeUpdate();
        return i;
    }
    @Override
    public List<ProductVO> selectBy12(Integer value){
        int itemsPerPage = 12;
        int skipItems = (value - 1) * itemsPerPage;

        Query query = session.createQuery("FROM ProductVO", ProductVO.class)
                .setFirstResult(skipItems)
                .setMaxResults(itemsPerPage);

        return query.getResultList();
    }

    @Override
    public int selectProductCount() {
        String hql = "SELECT COUNT(*) FROM ProductVO";
        TypedQuery<Long> query = session.createQuery(hql, Long.class);
        Long result = query.getSingleResult();
        int count = result.intValue(); // 轉換為 int
        return count;
    }


}
