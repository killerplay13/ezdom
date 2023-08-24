package tw.com.cha102.coachmember.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.cha102.coachmember.model.entity.CoachMemberVO;
import tw.com.cha102.coachmember.service.CoachMemberService;

@RestController
@RequestMapping("/coach")
public class CoachLoginController {

    @Autowired
    private CoachMemberService coachMemberService;

    @GetMapping("/{memberId}")
    public CoachMemberVO coachStatus(@PathVariable Integer memberId){
        return coachMemberService.getCoachStatus(memberId);
    }

}
