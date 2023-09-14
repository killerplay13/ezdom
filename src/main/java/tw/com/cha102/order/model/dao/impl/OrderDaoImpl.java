package tw.com.cha102.order.model.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import tw.com.cha102.member.model.entity.Member;
import tw.com.cha102.order.model.dao.OrderDao;
import tw.com.cha102.order.model.entity.OrderVO;
import tw.com.cha102.product.model.entity.ProductVO;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @PersistenceContext
    private Session session;

    @Override
    public Integer insert(OrderVO orderVO) {
        session.persist(orderVO);
        return orderVO.getOrderId(); // 返回新生成的 orderId
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

    @Override
    public Member selecMembertById(Integer memberId) {
        return session.get(Member.class,memberId);
    }


    @Override
    public List<OrderVO> selectByStatusTo3(Integer value,int orderStatus,Integer memberId) {
        int itemsPerPage = 3;
        int skipItems = (value - 1) * itemsPerPage;
        final String hql = "FROM OrderVO WHERE orderStatus = :orderStatus and memberId = :memberId ORDER BY orderId DESC";
        return session.createQuery(hql, OrderVO.class)
                .setParameter("orderStatus", (byte) orderStatus)
                .setParameter("memberId", memberId)
                .setFirstResult(skipItems)
                .setMaxResults(itemsPerPage)
                .getResultList();
    }

    @Override
    public int selectOrderCountByOrderStatus(int orderStatus,Integer memberId) {
        String hql = "SELECT COUNT(*) FROM OrderVO WHERE orderStatus=:orderStatus and memberId = :memberId";
        TypedQuery<Long> query = session.createQuery(hql, Long.class).setParameter("orderStatus",(byte)orderStatus).setParameter("memberId", memberId);
        Long result = query.getSingleResult();
        int count = result.intValue(); // 轉換為 int
        return count;
    }

    @Override
    public int updatePoint(Member member) {
        Query query = session.createQuery("UPDATE Member SET point=:point where memberId=:memberId")
                .setParameter("point", member.getPoint()).setParameter("memberId", member.getMemberId());
        int i = query.executeUpdate();
        return i;
    }

}
