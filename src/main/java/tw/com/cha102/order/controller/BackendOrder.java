package tw.com.cha102.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.order.model.entity.OrderVO;
import tw.com.cha102.order.service.OrderService;



import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/backend/oredr")
public class BackendOrder {
    @Autowired
    OrderService service;

    //訂單依狀態刷新後台頁面
    @GetMapping("/get/{orderStatus}")
    public List<OrderVO> getOrderByStatus(@PathVariable int orderStatus){
        List<OrderVO> list = new ArrayList<OrderVO>();
        list=service.findOrdersByStatus(orderStatus);
        return list;
    }

    //後台訂單商品明細
    @GetMapping("/product")
    public OrderVO getOrderProduct(@RequestParam Integer orderId){
        return service.selectOrderById(orderId);
    }

    //出貨按鈕
    @PutMapping("/ship/{orderId}")
    public  OrderVO orderToShip(@PathVariable Integer orderId){
        OrderVO orderVO=new OrderVO();
        if(service.toShipOrder(orderId)==true){
            orderVO.setMessage("操作成功");
            orderVO.setSuccessful(true);
        }else{
            orderVO.setMessage("操作失敗");
            orderVO.setSuccessful(false);
        }
        return orderVO;
    }
    //後台確認退貨按鈕 或買家退貨按鈕
    @PutMapping("/return/{orderId}")
    public  OrderVO orderToReturn(@PathVariable Integer orderId){
        OrderVO orderVO=new OrderVO();
        if(service.toRetrunOrder(orderId)==true){
            orderVO.setMessage("操作成功");
            orderVO.setSuccessful(true);
        }else{
            orderVO.setMessage("操作失敗");
            orderVO.setSuccessful(false);
        }
        return orderVO;
    }

}
