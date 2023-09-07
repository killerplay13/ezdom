package tw.com.cha102.ecpaydemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.cha102.ecpaydemo.service.ECPayorderService;
@CrossOrigin(origins = "*")
@RestController
public class ECPayorderController {

    @Autowired
    ECPayorderService orderService;

    @PostMapping("/ecpayCheckout")
    public String ecpayCheckout() {
        String aioCheckOutALLForm = orderService.ecpayCheckout();

        return aioCheckOutALLForm;
    }
}
