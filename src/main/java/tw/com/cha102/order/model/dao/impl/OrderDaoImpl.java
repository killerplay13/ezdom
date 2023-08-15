package tw.com.cha102.order.model.dao.impl;

import tw.com.cha102.order.model.dao.OrderDao;
import tw.com.cha102.order.model.entity.OrderVO;

import java.util.List;

public class OrderDaoImpl implements OrderDao{

    @Override
    public int insert(OrderVO orderVO) {
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public int update(OrderVO orderVO) {
        return 0;
    }

    @Override
    public OrderVO selectById(Integer id) {
        return null;
    }

    @Override
    public List<OrderVO> selectAll() {
        return null;
    }
}
