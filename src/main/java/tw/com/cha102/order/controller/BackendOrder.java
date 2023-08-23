package tw.com.cha102.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.order.model.entity.OrderVO;
import tw.com.cha102.order.service.OrderService;


import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/oredr")
public class BackendOrder {
    @Autowired
    OrderService service;

    //訂單依狀態刷新後台頁面
    @GetMapping("/get/{orderStatus}")
    public List<OrderVO> getProductByStatus(@PathVariable int orderStatus){
        List<OrderVO> list = new ArrayList<OrderVO>();
        list=service.findOrdersByStatus(orderStatus);
        return list;
    }

    //後台訂單商品明細
    @GetMapping("/product")
    public OrderVO getOrderProduct(@RequestParam Integer orderId){
        return service.selectOrderById(orderId);
    }
}
