package tw.com.cha102.reserve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.coachmember.model.entity.CoachMemberVO;
import tw.com.cha102.coachmember.service.CoachMemberService;
import tw.com.cha102.reserve.model.entity.ReserveItemVO;
import tw.com.cha102.reserve.service.ReserveService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/reserve")
public class ReserveController {

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private CoachMemberService coachMemberService;

    //新增項目
    @PostMapping("/addItem")
    public ReserveItemVO addReserveItem(@RequestBody ReserveItemVO reserveItemVO){
        Integer coachId=1;
        reserveItemVO.setCoachId(coachId);
        ReserveItemVO reserveItemVO1 = reserveService.insertReserveItem(reserveItemVO);
        reserveItemVO1.setMessage("新增項目成功");
        reserveItemVO1.setSuccessful(true);
        return reserveItemVO1;
    }
    //新增項目抓教練姓名
    @GetMapping("/coach/name")
    public CoachMemberVO findCoachNameById(){
        Integer coachId=1;
        return coachMemberService.getCoachMember(coachId);
    }
}
