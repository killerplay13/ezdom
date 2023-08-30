package tw.com.cha102.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.member.model.entity.Member;
import tw.com.cha102.order.model.entity.OrderVO;
import tw.com.cha102.order.service.OrderService;
import tw.com.cha102.product.model.entity.ProductVO;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/oredr")
public class FrontendOrder {

    @Autowired
    OrderService service;

    @GetMapping("/profile/{memberId}")
    public Member getMemberProfile(@PathVariable Integer memberId){
        return service.getGuestInformation(memberId);
    }

    //訂單新增
    @PostMapping("/add")
    public OrderVO addToOredr(@RequestBody OrderVO orderVO){
        OrderVO vo=new OrderVO();
        if(service.addToOrder(orderVO) ==true){
            vo.setSuccessful(true);
            vo.setMessage("結帳成功");
            return vo;
        }else{
            vo.setSuccessful(false);
            vo.setMessage("結帳失敗");
            return vo;
        }
    }
}
