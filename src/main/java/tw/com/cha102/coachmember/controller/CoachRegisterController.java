package tw.com.cha102.coachmember.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.coachmember.model.entity.CoachMemberVO;
import tw.com.cha102.coachmember.service.CoachMemberService;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/coach")
public class CoachRegisterController {

	@Autowired
	private CoachMemberService coachMemberService;

	@PostMapping("/register")
	public CoachMemberVO register(@Valid @RequestBody CoachMemberVO coachMemberVO){
		return coachMemberService.register(coachMemberVO);
	}
}
