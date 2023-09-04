package tw.com.cha102.order.service;

import tw.com.cha102.member.model.entity.Member;
import tw.com.cha102.order.model.entity.OrderVO;
import tw.com.cha102.product.model.entity.ProductVO;

import java.util.List;

public interface OrderService {

    List<OrderVO> findOrdersByStatus(int orderStatus);

    OrderVO selectOrderById(Integer orderId);

    boolean toShipOrder(Integer orderId);

    boolean toRetrunOrder(Integer orderId);

    Member getGuestInformation(Integer memberId);

    boolean addToOrder(OrderVO orderVO);

    List<OrderVO> findByStatusTo3(Integer value,int orderStatus);

    int findOrderCountByOrderStatus(Integer orderStatus);

    boolean confirmOrder(Integer orderId);

    boolean reduceMemberPoint(Integer memberId,Integer usePoints);

    boolean addMemberPoint(Integer memberId,Integer orderId);
}
