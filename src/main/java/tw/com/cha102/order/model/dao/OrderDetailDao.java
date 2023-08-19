package tw.com.cha102.order.model.dao;

import tw.com.cha102.order.model.entity.OrderDetailVO;



import java.util.List;

public interface OrderDetailDao {

    public int insert(OrderDetailVO orderDetailVO);

    public int deleteById(Integer orderDetailId);

    public int update(OrderDetailVO orderDetailVO);

    public OrderDetailVO selectById(Integer orderDetailId);

    public List<OrderDetailVO> selectAll();
}
