package tw.com.cha102.ecpaydemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.ecpaydemo.service.ECPayorderService;
import tw.com.cha102.order.model.entity.OrderVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/frontend")
public class ECPayorderController {

    @Autowired
    ECPayorderService orderService;

    @PostMapping("/ecpayCheckout")
    public String ecpayCheckout(@RequestBody OrderVO orderVO, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer memberId = (Integer)session.getAttribute("memberId");
        orderVO.setMemberId(memberId);
        String aioCheckOutALLForm = orderService.ecpayCheckout(orderVO);
        if(aioCheckOutALLForm == null){
            return "綠界付款失敗";
        }
        return aioCheckOutALLForm;
    }
}
