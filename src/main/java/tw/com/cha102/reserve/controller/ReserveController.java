package tw.com.cha102.reserve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.coachmember.model.entity.CoachMemberVO;
import tw.com.cha102.coachmember.service.CoachService;
import tw.com.cha102.reserve.model.dto.ReserveDTO;
import tw.com.cha102.reserve.model.dto.ReserveItemDTO;
import tw.com.cha102.reserve.model.dto.ReserveTime;
import tw.com.cha102.reserve.model.dto.ReserveTimeDTO;
import tw.com.cha102.reserve.model.entity.ReserveItemVO;
import tw.com.cha102.reserve.model.entity.ReserveTimeVO;
import tw.com.cha102.reserve.model.entity.ReserveVO;
import tw.com.cha102.reserve.service.ReserveService;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/frontend/reserve")
public class ReserveController {

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private CoachService coachService;

    // 教練新增預約項目 V
    @PostMapping("/addItem")
    public ReserveItemVO addReserveItem(@RequestBody ReserveItemVO reserveItemVO){
        Integer coachId=1;
        reserveItemVO.setCoachId(coachId);
        ReserveItemVO reserveItemVO1 = reserveService.insertReserveItem(reserveItemVO);
        reserveItemVO1.setMessage("新增項目成功");
        reserveItemVO1.setSuccessful(true);
        return reserveItemVO1;
    }
    // 教練新增項目抓教練姓名
//    @GetMapping("/coach/name")
//    public CoachMemberVO findCoachNameById(){
//        Integer coachId=1;
//        return coachMemberService.getCoachMember(coachId);
//    }

    // 預約專區、新增預約項: 抓取教練資訊 V
    @GetMapping("/coach/name/{coachId}")
    public CoachMemberVO findCoachNameByIdToMember(@PathVariable Integer coachId){
        return coachService.getCoachMember(coachId);
    }

    // 修改項目狀態 V
    @PutMapping("/coach/delete")
    public ReserveItemVO reveseReserveItemStatus(@RequestBody ReserveItemVO reserveItemVO){
        ReserveItemVO reserveItemVO1=new ReserveItemVO();
        if(reserveService.updateReserveItemStatus(reserveItemVO)){
            reserveItemVO1.setMessage("刪除成功");
            reserveItemVO1.setSuccessful(true);
        }
        return reserveItemVO1;
    }
    // 預約專區抓取教練的預約項目 V
    @GetMapping("/coach/item/{coachId}")
    public List<ReserveItemVO> findReserveItemByCoachId(@PathVariable Integer coachId){
        return reserveService.selectReserveItemByCoachId(coachId);
    }
    // 預約時段抓取教練可預約時間 V
    @GetMapping("/coach/time/{coachId}")
    public List<ReserveTimeVO> findReserveTimeByCoachId(@PathVariable Integer coachId){
        return reserveService.selectReserveTimeByCoachId(coachId);
    }

    // 新增預約訂單 V
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

    // 預約訂單成立後修改教練可預約時段的狀態 V
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
//    @GetMapping("/coach/list")
//    public List<ReserveItemVO> selectItemByReserveItemStatus(){
//        byte reserveItemStatus=1;
//        //後來從session拿
//        Integer coachId=1;
//        return reserveService.selectByReserveItemStatusAndCoachId(reserveItemStatus,coachId);
//    }

    // 會員預約訂單列表 V
    @GetMapping("/member/list")
    public List<ReserveItemDTO> memberReservationForm(){
        //之後從session抓
        Integer memberId = 1;
        return reserveService.findMemberReservationFormByMemberId(memberId);
    }
    // 會員完成預約訂單，修改訂單狀態 V
    @PutMapping("/member/compelete/{reserveId}")
    public ReserveVO completeReserve(@PathVariable Integer reserveId ,@RequestBody ReserveTimeDTO reserveTimeDTO){
        String dateStr = reserveTimeDTO.getDateStr();
        java.sql.Timestamp date = java.sql.Timestamp.valueOf(dateStr);
        Integer classTime = reserveTimeDTO.getClassTime();
        Integer coachId = reserveTimeDTO.getCoachId();
        ReserveVO reserveVO =new ReserveVO();
        if(reserveService.updateReserveStatusByReserveId(reserveId)&& 
           reserveService.updateAppointmentStatusByDateAndClassTimeAndCoachId(date,classTime,coachId)){
            reserveVO.setSuccessful(true);
            reserveVO.setMessage("訂單完成!");
            return reserveVO;
        }
        return null;
    }
    //教練排班表抓取教練可預約時間
//    @GetMapping("/coach/time")
//    public List<ReserveTimeVO> findReserveTimeByCoachId(){
//        Integer coachId=1;
//        return reserveService.selectReserveTimeByCoachId(coachId);
//    }
    // 教練排班表查看訂單詳情 V
    @GetMapping("/coach/details")
    public ReserveDTO getCoachReservationFormByCoachId(@RequestParam String reserveDate,@RequestParam byte reserveTime){
        //session抓coachId
        Integer coachId=1;
        return reserveService.getCoachReservationFormByCoachId(coachId,reserveDate,reserveTime);
    }

    // 教練排班，新增可預約時段 V
    @PostMapping("/coach/time")
    public ReserveTimeVO insetReserveTime(@RequestBody ReserveTimeVO reserveTimeVO,@RequestParam String dateStr){
        //後面從session抓
        java.sql.Timestamp date = java.sql.Timestamp.valueOf(dateStr);
        Integer coachId=1;
        reserveTimeVO.setCoachId(coachId);
        reserveTimeVO.setDate(date);
        ReserveTimeVO reserveTimeVO1 = reserveService.insetReserveTime(reserveTimeVO);
        reserveTimeVO1.setMessage("新增成功");
        reserveTimeVO1.setSuccessful(true);
        return reserveTimeVO1;
    }
    // 教練取消排班，刪除可預約時段 V
    @DeleteMapping("/coach/time/{reserveTimeId}")
    public ReserveTimeVO deleteReserveTime(@PathVariable Integer reserveTimeId){
        boolean check = reserveService.deleteReserveTime(reserveTimeId);
        ReserveTimeVO reserveTimeVO =new ReserveTimeVO();

        if(check){
            reserveTimeVO.setMessage("已取消排班");
            reserveTimeVO.setSuccessful(true);
        }
        return reserveTimeVO;
    }
}
