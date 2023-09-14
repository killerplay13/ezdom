package tw.com.cha102.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.member.model.entity.Member;
import tw.com.cha102.order.model.entity.OrderVO;
import tw.com.cha102.order.service.OrderService;
import tw.com.cha102.product.model.entity.ProductVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/frontend/oredr")
public class FrontendOrder {

    @Autowired
    OrderService service;
    //取得結帳客人資料
    @GetMapping("/profile")
    public Member getMemberProfile(HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer memberId = (Integer)session.getAttribute("memberId");
        return service.getGuestInformation(memberId);
    }

    //訂單新增
    @PostMapping("/add")
    public OrderVO addToOredr(@RequestBody OrderVO orderVO,HttpServletRequest request){
        HttpSession session = request.getSession();
        OrderVO vo=new OrderVO();
        Integer memberId = (Integer)session.getAttribute("memberId");
        orderVO.setMemberId(memberId);
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
    public List<OrderVO> getOrderByStatusLimit3(HttpServletRequest request,@PathVariable Integer value,@PathVariable int orderStatus){
        HttpSession session = request.getSession();
        Integer memberId = (Integer)session.getAttribute("memberId");
        List<OrderVO> list = new ArrayList<OrderVO>();
        list=service.findByStatusTo3(value,orderStatus,memberId);
        return list;
    }

    //前台依訂單數量 分頁
    @GetMapping("/sortPage")
    public int orderTotalCountByOrderStatus(HttpServletRequest request,@RequestParam Integer orderStatus){
        HttpSession session = request.getSession();
        Integer memberId = (Integer)session.getAttribute("memberId");
        return service.findOrderCountByOrderStatus(orderStatus,memberId);
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

    @PutMapping("/point/{usePoints}")
    public Member memberUsePoints(HttpServletRequest request, @PathVariable Integer usePoints){
        HttpSession session = request.getSession();
        Integer memberId = (Integer)session.getAttribute("memberId");
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

    @PutMapping("/addpoint/{orderId}")
    public Member memberAddPoints(HttpServletRequest request, @PathVariable Integer orderId){
        HttpSession session = request.getSession();
        Integer memberId = (Integer)session.getAttribute("memberId");
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

    //買家退貨按鈕
    @PutMapping("/return/{orderId}")
    public  OrderVO orderToReturnfront(@PathVariable Integer orderId){
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
