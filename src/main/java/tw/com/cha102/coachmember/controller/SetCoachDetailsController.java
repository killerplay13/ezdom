package tw.com.cha102.coachmember.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.coachmember.model.entity.CoachMemberVO;
import tw.com.cha102.coachmember.service.CoachMemberService;

@RestController
@RequestMapping("/coach/details")
public class SetCoachDetailsController {

    @Autowired
    private CoachMemberService coachMemberService;

    @PutMapping("/{coachId}")
    public CoachMemberVO setDetails(@PathVariable Integer coachId,
                                    @RequestBody CoachMemberVO updateDetails){
        return coachMemberService.updateDetails(coachId, updateDetails);
    }

}
