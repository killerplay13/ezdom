package tw.com.cha102.order.model.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import tw.com.cha102.order.model.dao.OrderDetailDao;
import tw.com.cha102.order.model.entity.OrderDetailVO;


import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class OrderDetailDaoImpl implements OrderDetailDao {

    @PersistenceContext
    private Session session;

    @Override
    public int insert(OrderDetailVO orderDetailVO) {
        session.persist(orderDetailVO);
        return 1;
    }

    @Override
    public int deleteById(Integer orderDetailId) {
        OrderDetailDao orderDetail = session.load(OrderDetailDao.class, orderDetailId);
        session.remove(orderDetail);
        return 1;
    }

    @Override
    public int update(OrderDetailVO orderDetailVO) {
        return 0;
    }

    @Override
    public OrderDetailVO selectById(Integer orderDetailId) {
        return session.get(OrderDetailVO.class,orderDetailId);
    }

    @Override
    public List<OrderDetailVO> selectAll() {
        final String hql="FROM OrderDetailVO ORDER BY orderDetailId";
        return session.createQuery(hql,OrderDetailVO.class).getResultList();
    }
}
