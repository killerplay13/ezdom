package tw.com.cha102.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.cha102.order.model.dao.OrderDao;
import tw.com.cha102.order.model.entity.OrderVO;
import tw.com.cha102.order.service.OrderService;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao dao;

    @Override
    public List<OrderVO> findOrdersByStatus(int orderStatus) {
        return dao.selectByStatus(orderStatus);
    }

    @Override
    public OrderVO selectOrderById(Integer orderId) {
        return dao.selectById(orderId);
    }

    @Override
    public boolean toShipOrder(Integer orderId) {
        OrderVO orderVO = dao.selectById(orderId);
        if(orderVO.getOrderStatus()==((byte) 0)){
            orderVO.setOrderStatus((byte) 1);
        }else if(orderVO.getOrderStatus()==((byte) 1)){
            orderVO.setOrderStatus((byte) 2);
        }
        return dao.updateToOrderStatus(orderVO)>0;
    }

    @Override
    public boolean toRetrunOrder(Integer orderId) {
        OrderVO orderVO = dao.selectById(orderId);
        if(orderVO.getOrderStatus()==((byte) 1)){
            orderVO.setOrderStatus((byte) 3);
        }else if(orderVO.getOrderStatus()==((byte) 3)){
            orderVO.setOrderStatus((byte) 4);
        }
        return dao.updateToOrderStatus(orderVO)>0;
    }
}
