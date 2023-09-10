package tw.com.cha102.grouppay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.group.model.GroupMember;
import tw.com.cha102.group.service.GroupMemberService;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/frontend/group")
public class ECPayGroupController {

    @Autowired
    ECPayGroupService ecPayGroupService;

    @Autowired
    GroupMemberService groupMemberService;

    @GetMapping("/ecpayCheckout")
    @ResponseBody
    public String ecpayCheckout(@RequestParam(value = "groupId") Integer groupId) {



        String aioCheckOutALLForm = ecPayGroupService.ecpayCheckout(groupId);
        if(aioCheckOutALLForm == null){
            return "綠界付款失敗";
        }
        return aioCheckOutALLForm;
    }

    @RequestMapping("/ecpayReturn")
    public String ecpayReturn(Model model, @RequestParam("RtnCode") String rtnCode,@RequestParam("CustomField1") String groupId) {

        // TODO: 用來獲取已登入的member訊息
        // Member member = (Member) session.getAttribute("member");
        // Integer memberId = member.getMemberId();

        //目前預設為1
        Integer memberId = 1;
        // rtnCode == 1 表示交易成功
        if ("1".equals(rtnCode)) {
            GroupMember groupMember = groupMemberService.findByGroupIdAndMemberIdAndGroupApplyStatus(Integer.parseInt(groupId), memberId, (byte) 5);
            if (!ObjectUtils.isEmpty(groupMember)) {
                groupMember.setGroupApplyStatus((byte) 4);
                groupMemberService.save(groupMember);
            }
            return "redirect:/group/grouppaysuccessful.html";
        } else {
            return "redirect:/group/grouppayfailed.html";
        }

    }
}
