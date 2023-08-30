package tw.com.cha102.coachmember.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.coachmember.model.dto.CoachDetails;
import tw.com.cha102.coachmember.model.dto.CoachList;
import tw.com.cha102.coachmember.model.entity.CoachMemberVO;
import tw.com.cha102.coachmember.service.CoachMemberService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/browse")
public class BrowseCoachController {

    @Autowired
    private CoachMemberService coachMemberService;

    @GetMapping("/list")
    public List<CoachList> browseCoachList(){
        return coachMemberService.getCoachList();
    }

    @GetMapping("/list/{coachId}")
    public CoachDetails browseCoach(@PathVariable Integer coachId){
        return coachMemberService.getCoachDetails(coachId);
    }

}
