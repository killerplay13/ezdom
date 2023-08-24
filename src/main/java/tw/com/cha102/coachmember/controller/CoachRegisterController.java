package tw.com.cha102.coachmember.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.cha102.coachmember.model.entity.CoachMemberVO;
import tw.com.cha102.coachmember.service.CoachMemberService;

import javax.validation.Valid;

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
