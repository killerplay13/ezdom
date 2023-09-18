package tw.com.cha102.grouppay;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.cha102.group.model.Group;
import tw.com.cha102.group.model.GroupMember;
import tw.com.cha102.group.service.GroupAdminService;
import tw.com.cha102.group.service.GroupMemberService;
import tw.com.cha102.order.model.dao.OrderDao;
import tw.com.cha102.order.model.dao.OrderDetailDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
@Transactional
public class ECPayGroupService {

    @Autowired
    OrderDao dao;
    @Autowired
    OrderDetailDao detail;

    @Autowired
    GroupMemberService groupMemberService;

    @Autowired
    GroupAdminService groupAdminService;





    public String ecpayCheckout(Integer groupId,Integer memberId) {

        List<Integer> grouIds = groupMemberService.findGroupIdsByMemberIdAndStatus(memberId, (byte) 1);
        if (grouIds.contains(groupId)) {
        }

        GroupMember groupMember = groupMemberService.findByGroupIdAndMemberIdAndGroupApplyStatus(groupId, memberId, (byte) 1);
        groupMember.setGroupApplyStatus((byte) 4);

        Group group = groupAdminService.getGroupById(groupId).get();

        List<String> nameList =new ArrayList<>();

        // Optional<String> reduce =nameList.stream().reduce((String acc , String curr)->{
        //     return acc+ "#" +curr;
        // });
        // String itemName=reduce.get();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String orderDate=sdf.format(new Date());
        AllInOne all = new AllInOne("");
        String uuId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
        AioCheckOutALL obj = new AioCheckOutALL();
        obj.setMerchantTradeNo(groupId+"EzDom"+uuId);
        obj.setMerchantTradeDate(orderDate);
        obj.setTotalAmount(group.getGroupDeposit().toString());
        obj.setTradeDesc("test Description");
        obj.setItemName(group.getGroupName());
        obj.setCustomField1(groupId.toString());
        // 交易結果回傳網址，只接受 https 開頭的網站，可以使用 ngrok
        obj.setReturnURL("http://localhost:8080/ezdom/group/grouppaysuccessful.html");
        obj.setNeedExtraPaidInfo("N");
        // 商店轉跳網址 (Optional)
//        obj.setOrderResultURL("http://localhost:8080/ezdom/frontend/group/ecpayReturn");
        obj.setClientBackURL("http://localhost:8080/ezdom/group/grouppaysuccessful.html");
        String form = all.aioCheckOut(obj, null);

        return form;
    }




}
