package tw.com.cha102.coachmember.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.coachmember.model.dto.CoachDetails;
import tw.com.cha102.coachmember.model.dto.CoachList;
import tw.com.cha102.coachmember.model.entity.CoachMemberVO;
import tw.com.cha102.coachmember.service.CoachMemberService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/verify")
public class CoachVerifyController {

    @Autowired
    private CoachMemberService coachMemberService;

    @GetMapping("/list")
    public List<CoachDetails> coachMemberList(@RequestParam(defaultValue = "1") Integer status,
                                              @RequestParam(defaultValue = "0") Integer page){
        return coachMemberService.getVerifyCoachList(status, page);
    }

//    @GetMapping("/list/{coachId}")
    public CoachMemberVO coachMember(@PathVariable Integer coachId){
        return coachMemberService.getCoachMember(coachId);
    }

    @PutMapping("/list/update/{coachId}")
    public CoachMemberVO verifyCoachMember(@PathVariable Integer coachId,
                                           @RequestBody byte status){

        return coachMemberService.updateStatus(coachId, status);
    }

}
