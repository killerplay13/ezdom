package tw.com.cha102.order.service;

import tw.com.cha102.order.model.entity.OrderVO;
import tw.com.cha102.product.model.entity.ProductVO;

import java.util.List;

public interface OrderService {

    public List<OrderVO> findOrdersByStatus(int orderStatus);

    public OrderVO selectOrderById(Integer orderId);

    public boolean toShipOrder(Integer orderId);

    public boolean toRetrunOrder(Integer orderId);
}
