package tw.com.cha102.coachmember.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.coachmember.model.dto.CoachDetails;
import tw.com.cha102.coachmember.model.entity.CoachMemberVO;
import tw.com.cha102.coachmember.service.CoachService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/backend/verify")
public class BackendCoachController {
    @Autowired
    private CoachService coachService;

    // ==================== 瀏覽教練列表 ==================== //
    @GetMapping("/list")
    public List<CoachDetails> coachMemberList(@RequestParam(defaultValue = "1") Integer status,
                                              @RequestParam(defaultValue = "0") Integer page){
        return coachService.getVerifyCoachList(status, page);
    }

    // ==================== 瀏覽教練列表的總頁數 ==================== //
    @GetMapping("/list/page")
    public Integer listPage(@RequestParam(defaultValue = "1") Integer status){
        return coachService.getListPage(status);
    }

    // ==================== 修改教練狀態 ==================== //
    @PutMapping("/list/update/{coachId}")
    public CoachMemberVO verifyCoachMember(@PathVariable Integer coachId,
                                           @RequestBody byte status){

        return coachService.updateStatus(coachId, status);
    }

}
