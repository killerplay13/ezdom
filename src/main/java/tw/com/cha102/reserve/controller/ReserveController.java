package tw.com.cha102.reserve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.coachmember.model.entity.CoachMemberVO;
import tw.com.cha102.coachmember.service.CoachMemberService;
import tw.com.cha102.reserve.model.dto.ReserveItemDTO;
import tw.com.cha102.reserve.model.dto.ReserveTime;
import tw.com.cha102.reserve.model.entity.ReserveItemVO;
import tw.com.cha102.reserve.model.entity.ReserveTimeVO;
import tw.com.cha102.reserve.model.entity.ReserveVO;
import tw.com.cha102.reserve.service.ReserveService;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

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
    //教練新增項目抓教練姓名
    @GetMapping("/coach/name")
    public CoachMemberVO findCoachNameById(){
        Integer coachId=1;
        return coachMemberService.getCoachMember(coachId);
    }

    //會員預約專區要抓教練資訊
    @GetMapping("/coach/name/{coachId}")
    public CoachMemberVO findCoachNameByIdToMember(@PathVariable Integer coachId){
        return coachMemberService.getCoachMember(coachId);
    }
    //會員預約專區  要抓教練教學項目
    @GetMapping("/coach/item/{coachId}")
    public List<ReserveItemVO> findReserveItemByCoachId(@PathVariable Integer coachId){
        return reserveService.selectReserveItemByCoachId(coachId);
    }
    //會員預約時間表抓取教練可預約時間
    @GetMapping("/coach/time/{coachId}")
    public List<ReserveTimeVO> findReserveTimeByCoachId(@PathVariable Integer coachId){
        return reserveService.selectReserveTimeByCoachId(coachId);
    }

    //新增預約訂單
    @PostMapping("/order")
    public ReserveVO addReserveOrder(@RequestBody ReserveVO reserveVO){
        //後面從session拿
        Integer memberId=1;
        reserveVO.setMemberId(memberId);
        ReserveVO reserveVO1 = reserveService.insertReserve(reserveVO);
        reserveVO1.setMessage("新增成功");
        reserveVO1.setSuccessful(true);
        return reserveVO1;
    }
    //會員新增訂單後修改教練可預約時間的狀態
    @PutMapping("/coach/time")
    public ReserveTimeVO reveseAppointmentStatus(@RequestBody @Valid ReserveTime reserveTime){
        Integer appointmentStatus=reserveTime.getAppointmentStatus();
        Timestamp date=reserveTime.getDate();
        Integer classTime=reserveTime.getClassTime();
        ReserveTimeVO reserveTimeVO =new ReserveTimeVO();
        if(reserveService.updateAppointmentStatus(appointmentStatus,date,classTime)==true){
            reserveTimeVO.setSuccessful(true);
            reserveTimeVO.setMessage("此時段修改成功");
        }else{
            reserveTimeVO.setSuccessful(false);
            reserveTimeVO.setMessage("此時段修改失敗");
        }
        return reserveTimeVO;
    }
    //教練本身項目查詢表
    @GetMapping("/coach/list")
    public List<ReserveItemVO> selectItemByReserveItemStatus(){
        byte reserveItemStatus=1;
        //後來從session拿
        Integer coachId=1;
        return reserveService.selectByReserveItemStatusAndCoachId(reserveItemStatus,coachId);
    }
    //修改項目狀態
    @PutMapping("/coach/delete")
    public ReserveItemVO reveseReserveItemStatus(@RequestBody ReserveItemVO reserveItemVO){
        ReserveItemVO reserveItemVO1=new ReserveItemVO();
        if(reserveService.updateReserveItemStatus(reserveItemVO)){
            reserveItemVO1.setMessage("刪除成功");
            reserveItemVO1.setSuccessful(true);
        }
        return reserveItemVO1;
    }
    //會員預約單
    @GetMapping("/member/list")
    List<ReserveItemDTO> memberReservationForm(){
        //之後從session抓
        Integer memberId=1;
        return reserveService.findMemberReservationFormByMemberId(memberId);
    }
}
