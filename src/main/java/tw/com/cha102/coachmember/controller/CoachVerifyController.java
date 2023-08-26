package tw.com.cha102.coachmember.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.coachmember.model.entity.CoachMemberVO;
import tw.com.cha102.coachmember.service.CoachMemberService;

import java.util.List;

@RestController
@RequestMapping("/verify")
public class CoachVerifyController {

    @Autowired
    private CoachMemberService coachMemberService;

    @GetMapping("/list")
    public List<CoachMemberVO> coachMemberList(){
        return coachMemberService.getCoachMemberList();
    }

    @GetMapping("/list/{coachId}")
    public CoachMemberVO coachMember(@PathVariable Integer coachId){
        return coachMemberService.getCoachMember(coachId);
    }

    @PutMapping("/list/update/{coachId}")
    public CoachMemberVO verifyCoachMember(@PathVariable Integer coachId,
                                           @RequestBody CoachMemberVO newCoachMember){

        return coachMemberService.updateStatus(coachId, newCoachMember);
    }

}
