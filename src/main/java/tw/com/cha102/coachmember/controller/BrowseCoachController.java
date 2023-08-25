package tw.com.cha102.coachmember.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.cha102.coachmember.model.entity.CoachMemberVO;
import tw.com.cha102.coachmember.service.CoachMemberService;

import java.util.List;

@RestController
@RequestMapping("/browse")
public class BrowseCoachController {

    @Autowired
    private CoachMemberService coachMemberService;

    @GetMapping("/list")
    public List<CoachMemberVO> browseCoachList(){
        return coachMemberService.getCoachMemberList();
    }

    @GetMapping("/list/{coachId}")
    public CoachMemberVO browseCoach(@PathVariable Integer coachId){
        return coachMemberService.getCoachMember(coachId);
    }

}
