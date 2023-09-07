package tw.com.cha102.ecpaydemo.service;

import org.springframework.stereotype.Service;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;

import java.util.UUID;


@Service
public class ECPayorderService {

    public String ecpayCheckout() {
        String uuId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
        AllInOne all = new AllInOne("");

        AioCheckOutALL obj = new AioCheckOutALL();
        obj.setMerchantTradeNo(uuId);
        obj.setMerchantTradeDate("2017/01/01 08:05:23");
        obj.setTotalAmount("50");
        obj.setTradeDesc("test Description");
        obj.setItemName("TestItem");
        // 交易結果回傳網址，只接受 https 開頭的網站，可以使用 ngrok
         obj.setReturnURL("http://localhost:8080/ezdom/frontendshop/shop-account-orders.html");
        obj.setNeedExtraPaidInfo("N");
        // 商店轉跳網址 (Optional)
        obj.setClientBackURL("http://localhost:8080/ezdom/frontendshop/shop-account-orders.html");
        String form = all.aioCheckOut(obj, null);

        return form;
    }
}
