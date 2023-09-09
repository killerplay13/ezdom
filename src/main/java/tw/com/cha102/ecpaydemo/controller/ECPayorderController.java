package tw.com.cha102.ecpaydemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.ecpaydemo.service.ECPayorderService;
import tw.com.cha102.order.model.entity.OrderVO;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/frontend")
public class ECPayorderController {

    @Autowired
    ECPayorderService orderService;

    @PostMapping("/ecpayCheckout")
    public String ecpayCheckout(@RequestBody OrderVO orderVO) {
        String aioCheckOutALLForm = orderService.ecpayCheckout(orderVO);
        if(aioCheckOutALLForm == null){
            return "綠界付款失敗";
        }
        return aioCheckOutALLForm;
    }
}
