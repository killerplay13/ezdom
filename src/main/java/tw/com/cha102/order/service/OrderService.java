package tw.com.cha102.order.service;

import tw.com.cha102.order.model.entity.OrderVO;
import tw.com.cha102.product.model.entity.ProductVO;

import java.util.List;

public interface OrderService {

    List<OrderVO> findOrdersByStatus(int orderStatus);

    OrderVO selectOrderById(Integer orderId);

    boolean toShipOrder(Integer orderId);

    boolean toRetrunOrder(Integer orderId);
}
