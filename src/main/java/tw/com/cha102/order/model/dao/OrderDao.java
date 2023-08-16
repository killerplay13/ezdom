package tw.com.cha102.order.model.dao;

import org.springframework.stereotype.Repository;
import tw.com.cha102.order.model.entity.OrderVO;

import java.util.List;

public interface OrderDao {

    public int insert(OrderVO orderVO);

    public int deleteById(Integer orderId);

    public int updateToOrderStatus(OrderVO orderVO);

    public OrderVO selectById(Integer orderId);

    List<OrderVO> selectAll();
}
