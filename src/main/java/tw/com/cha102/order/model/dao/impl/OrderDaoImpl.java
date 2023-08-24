package tw.com.cha102.order.model.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import tw.com.cha102.order.model.dao.OrderDao;
import tw.com.cha102.order.model.entity.OrderVO;
import tw.com.cha102.product.model.entity.ProductVO;

import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @PersistenceContext
    private Session session;

    @Override
    public int insert(OrderVO orderVO) {
        session.persist(orderVO);
        return 1;
    }

    @Override
    public int deleteById(Integer orderId) {
        OrderVO order = session.load(OrderVO.class, orderId);
        session.remove(order);
        return 1;
    }

    @Override
    public int updateToOrderStatus(OrderVO orderVO) {
        Query query = session.createQuery("UPDATE OrderVO SET orderStatus=:orderStatus where orderId=:orderId")
                .setParameter("orderStatus", orderVO.getOrderStatus()).setParameter("orderId", orderVO.getOrderId());
        int i = query.executeUpdate();
        return i;
    }

    @Override
    public OrderVO selectById(Integer orderId) {
        return session.get(OrderVO.class,orderId);
    }

    @Override
    public List<OrderVO> selectAll() {
        final String hql = "FROM OrderVO ORDER BY orderId";
        return session
                .createQuery(hql, OrderVO.class)
                .getResultList();
    }

    @Override
    public List<OrderVO> selectByStatus(int orderStatus) {
        final String hql = "FROM OrderVO WHERE orderStatus = :orderStatus ORDER BY orderId";
        return session.createQuery(hql, OrderVO.class)
                .setParameter("orderStatus", (byte) orderStatus)
                .getResultList();
    }

}
