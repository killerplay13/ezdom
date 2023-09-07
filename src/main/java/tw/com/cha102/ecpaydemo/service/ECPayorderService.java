package tw.com.cha102.ecpaydemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import org.springframework.transaction.annotation.Transactional;
import tw.com.cha102.order.model.dao.OrderDao;
import tw.com.cha102.order.model.dao.OrderDetailDao;
import tw.com.cha102.order.model.entity.OrderDetailVO;
import tw.com.cha102.order.model.entity.OrderVO;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Transactional
public class ECPayorderService {

    @Autowired
    OrderDao dao;
    @Autowired
    OrderDetailDao detail;


    public String ecpayCheckout(OrderVO orderVO) {
        List<String> nameList =new ArrayList<>();

        Integer orderId = dao.insert(orderVO);//新生成訂單的id
        List<OrderDetailVO> orderDetailVOs = orderVO.getOrderDetailVOs();
        for(OrderDetailVO orderDetailVO : orderDetailVOs){
            nameList.add(orderDetailVO.getProductName());
            orderDetailVO.setOrderId(orderId);
            detail.insert(orderDetailVO);
        }
        Optional<String> reduce =nameList.stream().reduce((String acc , String curr)->{
            return acc+ "#" +curr;
        });
        String itemName=reduce.get();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String orderDate=sdf.format(new Date());
        AllInOne all = new AllInOne("");

        AioCheckOutALL obj = new AioCheckOutALL();
        obj.setMerchantTradeNo(orderId+"EzDom");
        obj.setMerchantTradeDate(orderDate);
        obj.setTotalAmount(orderVO.getActualAmount().toString());
        obj.setTradeDesc("test Description");
        obj.setItemName(itemName);
        // 交易結果回傳網址，只接受 https 開頭的網站，可以使用 ngrok
         obj.setReturnURL("http://localhost:8080/ezdom/frontendshop/shop-account-orders.html");
        obj.setNeedExtraPaidInfo("N");
        // 商店轉跳網址 (Optional)
        obj.setClientBackURL("http://localhost:8080/ezdom/frontendshop/shop-account-orders.html");
        String form = all.aioCheckOut(obj, null);

        return form;
    }
}
