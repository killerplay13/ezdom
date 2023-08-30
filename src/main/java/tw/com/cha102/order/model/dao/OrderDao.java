package tw.com.cha102.order.model.dao;


import tw.com.cha102.member.model.entity.Member;
import tw.com.cha102.order.model.entity.OrderVO;

import java.util.List;

public interface OrderDao {

    Integer insert(OrderVO orderVO);

    int deleteById(Integer orderId);

    int updateToOrderStatus(OrderVO orderVO);

    OrderVO selectById(Integer orderId);

    List<OrderVO> selectAll();

    List<OrderVO> selectByStatus(int orderStatus);

    Member selecMembertById(Integer memberId);

}
