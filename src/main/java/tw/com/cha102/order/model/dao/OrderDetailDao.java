package tw.com.cha102.order.model.dao;

import tw.com.cha102.order.model.entity.OrderDetailVO;



import java.util.List;

public interface OrderDetailDao {

    int insert(OrderDetailVO orderDetailVO);

    int deleteById(Integer orderDetailId);

    int update(OrderDetailVO orderDetailVO);

    OrderDetailVO selectById(Integer orderDetailId);

    List<OrderDetailVO> selectAll();
}
