package tw.com.cha102.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.member.model.entity.Member;
import tw.com.cha102.order.model.entity.OrderVO;
import tw.com.cha102.order.service.OrderService;
import tw.com.cha102.product.model.entity.ProductVO;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/frontend/oredr")
public class FrontendOrder {

    @Autowired
    OrderService service;
    //取得結帳客人資料
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

    //依訂單狀態刷新
    @GetMapping("/limit3/{value}/{orderStatus}")
    public List<OrderVO> getOrderByStatusLimit3(@PathVariable Integer value,@PathVariable int orderStatus){
        List<OrderVO> list = new ArrayList<OrderVO>();
        list=service.findByStatusTo3(value,orderStatus);
        return list;
    }

    //前台依訂單數量 分頁
    @GetMapping("/sortPage")
    public int orderTotalCountByOrderStatus(@RequestParam Integer orderStatus){
        return service.findOrderCountByOrderStatus(orderStatus);
    }
    //前台完成訂單
    @PutMapping("/confirm/{orderId}")
    public  OrderVO orderToReturn(@PathVariable Integer orderId){
        OrderVO orderVO=new OrderVO();
        if(service.confirmOrder(orderId)==true){
            orderVO.setMessage("操作成功");
            orderVO.setSuccessful(true);
        }else{
            orderVO.setMessage("操作失敗");
            orderVO.setSuccessful(false);
        }
        return orderVO;
    }

    @PutMapping("/point/{memberId}/{usePoints}")
    public Member memberUsePoints(@PathVariable Integer memberId, @PathVariable Integer usePoints){
        Member member=new Member();
        if(service.reduceMemberPoint(memberId,usePoints)==true){
            member.setMessage("操作成功");
            member.setSuccessful(true);
        }else{
            member.setMessage("操作失敗");
            member.setSuccessful(false);
        }
        return member;
    }

    @PutMapping("/addpoint/{memberId}/{orderId}")
    public Member memberAddPoints(@PathVariable Integer memberId, @PathVariable Integer orderId){
        Member member=new Member();
        if(service.addMemberPoint(memberId,orderId)==true){
            member.setMessage("操作成功");
            member.setSuccessful(true);
        }else{
            member.setMessage("操作失敗");
            member.setSuccessful(false);
        }
        return member;
    }
}
