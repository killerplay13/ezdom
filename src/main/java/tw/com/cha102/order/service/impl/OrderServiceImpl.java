package tw.com.cha102.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.cha102.member.model.entity.Member;
import tw.com.cha102.order.model.dao.OrderDao;
import tw.com.cha102.order.model.dao.OrderDetailDao;
import tw.com.cha102.order.model.entity.OrderDetailVO;
import tw.com.cha102.order.model.entity.OrderVO;
import tw.com.cha102.order.service.OrderService;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao dao;
    @Autowired
    OrderDetailDao detail;

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
        if(orderVO.getOrderStatus()==((byte) 3)){
            orderVO.setOrderStatus((byte) 4);
        }
        if(orderVO.getOrderStatus()==((byte) 1)||orderVO.getOrderStatus()==((byte) 0)){
            orderVO.setOrderStatus((byte) 3);
        }
        return dao.updateToOrderStatus(orderVO)>0;
    }

    @Override
    public Member getGuestInformation(Integer memberId) {
        return dao.selecMembertById(memberId);
    }
    //結帳新增訂單
    @Override
    public boolean addToOrder(OrderVO orderVO) {
        Integer orderId = dao.insert(orderVO);
        List<OrderDetailVO> orderDetailVOs = orderVO.getOrderDetailVOs();
        for(OrderDetailVO orderDetailVO : orderDetailVOs){
            orderDetailVO.setOrderId(orderId);
            detail.insert(orderDetailVO);
        }
        
        return true;
    }

    @Override
    public List<OrderVO> findByStatusTo3(Integer value, int orderStatus) {
        return dao.selectByStatusTo3(value,orderStatus);
    }

    @Override
    public int findOrderCountByOrderStatus(Integer orderStatus) {
        return dao.selectOrderCountByOrderStatus(orderStatus);
    }
    //前台完成訂單
    @Override
    public boolean confirmOrder(Integer orderId) {
        OrderVO orderVO = dao.selectById(orderId);
        if(orderVO.getOrderStatus()==((byte) 1)){
            orderVO.setOrderStatus((byte) 2);
        }
        return dao.updateToOrderStatus(orderVO)>0;
    }

    @Override
    public boolean reduceMemberPoint(Integer memberId, Integer usePoints) {
        Member member = dao.selecMembertById(memberId);
        Integer point = member.getPoint();
        Integer remainPoints =point-usePoints;
        member.setPoint(remainPoints);
        return dao.updatePoint(member)>0;
    }

    @Override
    public boolean addMemberPoint(Integer memberId, Integer orderId) {
        Member member = dao.selecMembertById(memberId);
        Integer point = member.getPoint();
        OrderVO orderVO = dao.selectById(orderId);
        Integer backPoints = orderVO.getBackPoints();
        member.setPoint(point+backPoints);
        return dao.updatePoint(member)>0;
    }
}
